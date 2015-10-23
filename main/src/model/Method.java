package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 11-07-2015
 */
public class Method extends ContentContainer {
	private final String name;
	private final Module module;
	private final Integer loc;
	private final Boolean isAccessor;
	private final List<String> params;

	private final Set<String> referencedInstanceVariables;

	public Method(String name, Module module, Integer loc, List<String> params, Boolean isAccessor) {
		this.name = name;
		this.module = module;
		this.loc = loc;
		this.params = params;
		this.isAccessor = isAccessor;

		this.referencedInstanceVariables = new HashSet<>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Module getModule() {
		return this.module;
	}

	public Integer getLoc() {
		return this.loc;
	}

	public Integer getAid() {
		//Access of Import Data: Number of data members accessed in a method directly or via accessor-methods, from which the definition-class of the method is not derived.
		//TODO: add used accessors
		return this.referencedVariables.size() - this.getReferencedInstanceVariables().size();
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
		Set<String> copyReferencedVars = this.referencedVariables.stream().collect(Collectors.toSet());
		copyReferencedVars.removeAll(this.referencedInstanceVariables);
		return copyReferencedVars;
	}

	public Boolean isAccessor() {
		return this.isAccessor;
	}

	public Boolean hasVariableIntersection(Method m) {
		return !Collections.disjoint(this.referencedInstanceVariables, m.getReferencedInstanceVariables());
	}

	public Boolean isPrivate() {
		return this.name.startsWith("__") && !this.name.endsWith("__");
	}

	public void markVarsAsClassVars(Set<String> classVars) {
		for (String var : classVars) {
			if (this.referencedVariables.contains(var)) {
				this.referencedInstanceVariables.add(var);
			}
		}
	}

	public void addAssign(String target, String source) {
		if (!this.assignVars.containsKey(target)) {
			this.assignVars.put(target, new HashSet<>());
		}
		this.assignVars.get(target).add(source);
	}
}
