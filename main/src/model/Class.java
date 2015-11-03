package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 30-06-2015
 */
public class Class extends ContentContainer {

	private final Integer loc;
	private final ContentContainer parent;
	private final List<String> parentNames;
	private final Map<String, Class> parents;

	private final VarDefinitions inheritedVars;

	public Class(String name, ContentContainer parent, Integer loc, List<String> parentNames) {
		super(name);
		this.loc = loc;
		this.parent = parent;
		this.parentNames = parentNames;
		this.parents = new HashMap<>();

		this.inheritedVars = new VarDefinitions();
	}

	@Override
	public String getName() {
		return this.name;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer getLcom() {
		return this.calculateLcom();
	}

	public Map<String, Class> getParents() {
		return this.parents;
	}

	public Set<Class> getParentsSet() {
		return this.parents.values().stream().collect(Collectors.toSet());
	}

	@Override
	public VarDefinitions getDefinedVarsInclParentsVars() {
		VarDefinitions vars = new VarDefinitions();
		this.parents.values().forEach(p -> vars.addAllUnrestricted(p.getDefinedVarsInclParentsVars()));
		vars.addAllEnforceRestriction(this.inheritedVars);
		vars.addAllEnforceRestriction(this.definedVars);
		return vars;
	}

	public List<String> getParentNames() {
		return this.parentNames;
	}

	public Integer parentsCount() {
		return this.parentNames.size();
	}

	private Boolean privateFieldsWithOnePublicMethod() {
		return //this.privateVariablesCount() > 10 &&
				this.getDefinedSubroutinesSet().stream().filter(s -> !s.isPrivate()).count() == 1;
	}

	private Integer calculateLcom() {
		int intersectDifference = 0; // = not intersecting amount - intersecting amount
		List<Subroutine> ms = this.definedSubroutines.values().stream().collect(Collectors.toList());
		for (int i = 0; i < ms.size(); i++) {
			for (int j = i + 1; j > i && j < ms.size(); j++) {
				intersectDifference += this.lcomIntersectDifference(ms.get(i), ms.get(j));
			}
		}
		return Math.max(0, intersectDifference);
	}

	private Integer lcomIntersectDifference(Subroutine a, Subroutine b) {
		return a.hasVariableIntersection(b) ? -1 : 1;
	}

	@Override
	public void resolveInheritance(Scope scope) {
		super.resolveInheritance(scope);
		for (String clsName : this.parentNames) {
			if (scope.definedClasses.containsKey(clsName)) {
				Class parentCls = scope.definedClasses.get(clsName);
				if (!parentCls.isInInheritanceLine(this)) {
					this.parents.put(clsName, parentCls);
					this.referencedClasses.put(clsName, parentCls);
				}
			}
		}
	}

	@Override
	public void copyParentVars() {
		super.copyParentVars();
		for (Class parent : this.parents.values()) {
			VarDefinitions parentVars = parent.getParentVars();

			for (String varName : parentVars.getNames()) {
				this.addInheritedVars(parentVars, varName);
			}
		}
	}

	private void addInheritedVars(VarDefinitions parentVars, String varName) {
		for (Variable var : parentVars.getVarsWithName(varName)) {
			Variable copy = new Variable(var.getName(), this, var.getVarType());
			this.inheritedVars.addUnrestricted(varName, copy);
		}
	}

	private VarDefinitions getParentVars() {
		VarDefinitions vars = new VarDefinitions();
		for (Class parent : this.parents.values()) {
			vars.addAllUnrestricted(parent.getParentVars());
		}
		vars.addAllUnrestricted(this.definedVars);
		return vars;
	}

	private boolean isInInheritanceLine(ContentContainer container) {
		if (this.equals(container)) {
			return true;
		}
		for (Class parent : this.parents.values()) {
			if (parent.isInInheritanceLine(container)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isInParentLine(ContentContainer container) {
		if (this.equals(container)) {
			return true;
		}
		return this.parent.isInParentLine(container);
	}
}
