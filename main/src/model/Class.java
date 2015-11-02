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

	private final Map<String, Set<Variable>> inheritedVars;

	public Class(String name, ContentContainer parent, Integer loc, List<String> parentNames) {
		super(name);
		this.loc = loc;
		this.parent = parent;
		this.parentNames = parentNames;
		this.parents = new HashMap<>();

		this.inheritedVars = new HashMap<>();
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

	@Override
	public Map<String, Set<Variable>> getDefinedVarsInclParentsVars() {
		Map<String, Set<Variable>> vars = new HashMap<>();
		this.add(vars, this.inheritedVars);
		this.parents.values().forEach(p -> this.add(vars, p.getDefinedVarsInclParentsVars()));
		this.add(vars, super.getDefinedVarsInclParentsVars());
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
				this.parents.put(clsName, scope.definedClasses.get(clsName));
				this.referencedClasses.put(clsName, this.parents.get(clsName));
			}
		}
	}

	@Override
	public void copyParentVars() {
		super.copyParentVars();
		for (Class parent : this.parents.values()) {
			Map<String, Set<Variable>> parentVars = parent.getParentVars();
			for (String varName : parentVars.keySet()) {
				this.addInheritedVars(parentVars, varName);
			}
		}
	}

	private void addInheritedVars(Map<String, Set<Variable>> parentVars, String varName) {
		if (!this.inheritedVars.containsKey(varName)) {
			this.inheritedVars.put(varName, new HashSet<>());
		}
		for (Variable var : parentVars.get(varName)) {
			Variable copy = new Variable(var.getName(), this, var.getVarType());
			this.inheritedVars.get(varName).add(copy);
		}
	}

	private Map<String, Set<Variable>> getParentVars() {
		Map<String, Set<Variable>> vars = new HashMap<>();
		for (Class parent : this.parents.values()) {
			this.add(vars, parent.getParentVars());
		}
		this.add(vars, this.definedVars);
		return vars;
	}

	private void add(Map<String, Set<Variable>> target, Map<String, Set<Variable>> values) {
		for (String name : values.keySet()) {
			if (!target.containsKey(name)) {
				target.put(name, new HashSet<>());
			}
			target.get(name).addAll(values.get(name));
		}
	}

	@Override
	public boolean isInParentLine(ContentContainer container) {
		if (this.equals(container)) {
			return true;
		}
		return this.parent.isInParentLine(container);
	}
}
