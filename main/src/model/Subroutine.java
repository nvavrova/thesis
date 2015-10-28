package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 11-07-2015
 */
public class Subroutine extends ContentContainer {
	private final Integer loc;
	private final ContentContainer parent;
	private final SubroutineType subroutineType;
	private final Boolean isAccessor;
	private final List<String> params;

	private final Set<String> referencedInstanceVariables;

	public Subroutine(String name, ContentContainer parent, Integer loc, SubroutineType subroutineType, List<String> params, Boolean isAccessor) {
		super(name);
		this.loc = loc;
		this.parent = parent;
		this.subroutineType = subroutineType;
		this.params = params;
		this.isAccessor = isAccessor;

		this.referencedInstanceVariables = new HashSet<>();
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer getAid() {
		//Access of Import Data: Number of data members accessed in a method directly or via accessor-methods, from which the definition-class of the method is not derived.
		//TODO: add used accessors
		return this.referencedVarNames.size() - this.getReferencedInstanceVariables().size();
	}

	public Integer getAld() {
		//Access of Local Data: Number of the data members accessed in the given method, which are local to the class where the method is defined.
		return this.getReferencedInstanceVariables().size();
	}

	public Integer paramCount() {
		return this.params.size();
	}

	public Set<String> getReferencedInstanceVariables() {
		return this.referencedInstanceVariables;
	}

	public Set<String> getUsedNonInstanceVars() {
		Set<String> copyReferencedVars = this.referencedVarNames.stream().collect(Collectors.toSet());
		copyReferencedVars.removeAll(this.referencedInstanceVariables);
		return copyReferencedVars;
	}

	public Boolean isAccessor() {
		return this.isAccessor;
	}

	public SubroutineType getSubroutineType() {
		return this.subroutineType;
	}

	public Boolean hasVariableIntersection(Subroutine m) {
		return !Collections.disjoint(this.referencedInstanceVariables, m.getReferencedInstanceVariables());
	}

	public Boolean isPrivate() {
		return this.name.startsWith("__") && !this.name.endsWith("__");
	}

	@Override
	public boolean isInAncestorLine(ContentContainer container) {
		if (this.equals(container)) {
			return true;
		}
		return this.parent.isInAncestorLine(container);
	}
}
