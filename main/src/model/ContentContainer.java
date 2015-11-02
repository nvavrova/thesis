package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 20-10-2015
 */
public abstract class ContentContainer extends ContentDefinitions {

	protected final String name;

	protected final Map<String, Set<Variable>> referencedVars;

	protected final Set<String> referencedVarNames;
	protected final Map<String, Subroutine> calledSubroutines;

	protected final Set<String> calledSubroutineNames;
	protected final Map<String, Class> referencedClasses;

	protected final List<Assign> assigns;

	public ContentContainer(String name) {
		super();
		this.name = name;

		this.referencedVars = new HashMap<>();
		this.referencedVarNames = new HashSet<>();

		this.calledSubroutines = new HashMap<>();
		this.calledSubroutineNames = new HashSet<>();

		this.referencedClasses = new HashMap<>();

		this.assigns = new ArrayList<>();
	}


	//-----------------------------------------------------------------------------------------------------\\
	//---------------------------------------------- GETTERS ----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\

	public String getName() {
		return this.name;
	}

	public Set<Variable> getReferencedVariablesSet() {
		Set<Variable> refVars = new HashSet<>();
		this.referencedVars.values().forEach(refVars::addAll);
		this.getChildren().forEach(c -> refVars.addAll(c.getReferencedVariablesSet()));
		return refVars;
	}

	public Set<Variable> getReferencedGlobalsSet() {
		return this.getReferencedVariablesSet().stream()
				.filter(v -> v.getVarType() == VarType.GLOBAL || (v.getVarType() == VarType.LOCAL && !v.definedInParentOf(this)))
				.collect(Collectors.toSet());
	}

	public Set<Class> getReferencedClassesSet() {
		Set<Class> classes = this.referencedClasses.values().stream().collect(Collectors.toSet());
		this.getChildren().forEach(c -> classes.addAll(c.getReferencedClassesSet()));
		return classes;
	}

	public Integer subroutinesWithNoParamsCount() {
		Long count = this.definedSubroutines.values().stream()
				.filter(m -> m.paramCount() == 0)
				.count();
		return count.intValue();
	}

	//-----------------------------------------------------------------------------------------------------\\
	//---------------------------------------------- ADDERS -----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	public void addVariableReference(String varName) {
		this.referencedVarNames.add(varName);
	}

	public void addSubroutineDefinition(Subroutine subroutine) {
		this.definedSubroutines.put(subroutine.getName(), subroutine);
	}

	public void addSubroutineCall(String subroutineName) {
		this.calledSubroutineNames.add(subroutineName);
	}

	public void addClassDefinition(Class cls) {
		this.definedClasses.put(cls.getName(), cls);
	}

	protected void addVariableReference(String name, Variable var) {
		if (!this.referencedVars.containsKey(name)) {
			this.referencedVars.put(name, new HashSet<>());
		}
		this.referencedVars.get(name).add(var);
	}

	public void addAssign(Assign assign) {
		this.assigns.add(assign);
	}

	//-----------------------------------------------------------------------------------------------------\\
	//----------------------------------------------- OTHER -----------------------------------------------\\
	//-----------------------------------------------------------------------------------------------------\\
	public void resolveDependencies(Scope scope) {
		this.resolveDependencies("", scope);

		Scope classScope = scope.copy();
		classScope.addToScope(this.name, this, false);
		for (Class cls : this.definedClasses.values()) {
			if (!cls.equals(this)) {
				cls.resolveDependencies(classScope);
			}
		}

		Scope methodScope = scope.copy();
		methodScope.addToScope("", this, true);
		for (Subroutine m : this.definedSubroutines.values()) {
			if (!m.equals(this)) {
				m.resolveDependencies(methodScope);
			}
		}
	}

	public void resolveInheritance(Scope scope) {
		this.getChildren().forEach(c -> c.resolveInheritance(scope));
	}

	public void copyParentVars() {
		this.getChildren().forEach(c -> c.copyParentVars());
	}

	private void resolveDependencies(String prefix, ContentDefinitions scopePart) {
		if (scopePart.equals(this)) {
			return;
		}
		this.resolveClassDependenciesAndReferences(prefix, scopePart);
		this.resolveSubroutineDependenciesAndReferences(prefix, scopePart);
		this.resolveVarReferences(prefix, scopePart);
	}

	private void resolveClassDependenciesAndReferences(String prefix, ContentDefinitions scopePart) {
		for (String alias : scopePart.definedClasses.keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			Class cls = scopePart.definedClasses.get(alias);

			if (this.calledSubroutineNames.contains(prefixedAlias)) {
				this.referencedClasses.put(prefixedAlias, cls);
				this.resolveInstanceDependencies(prefixedAlias, cls);
			}
			this.resolveDependencies(prefixedAlias, cls);
		}
	}

	private void resolveSubroutineDependenciesAndReferences(String prefix, ContentDefinitions scopePart) {
		for (String alias : scopePart.definedSubroutines.keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			Subroutine subroutine = scopePart.definedSubroutines.get(alias);

			if (this.calledSubroutineNames.contains(prefixedAlias)) {
				this.calledSubroutines.put(prefixedAlias, subroutine);
			}
			this.resolveDependencies(prefixedAlias, subroutine);
		}
	}

	private void resolveVarReferences(String prefix, ContentDefinitions scopePart) {
		for (String alias : scopePart.getDefinedVarsInclParentsVars().keySet()) {
			String prefixedAlias = this.prefix(alias, prefix);
			if (this.referencedVarNames.contains(prefixedAlias)) {
				for (Variable var : scopePart.getDefinedVarsInclParentsVars().get(alias)) {
					this.addVariableReference(prefixedAlias, var);
				}
			}
		}
	}

	private void resolveInstanceDependencies(String prefixedAlias, Class cls) {
		for (Assign assign : this.assigns) {
			if (assign.getValue().equals(prefixedAlias)) {
				this.resolveDependencies(assign.getName(), cls);
			}
		}
	}

	private String prefix(String str, String prefix) {
		return prefix.equals("") ? str : prefix + "." + str;
	}

	public abstract boolean isInParentLine(ContentContainer container);
}
