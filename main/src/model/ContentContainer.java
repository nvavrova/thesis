package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Nik on 20-10-2015
 */
public abstract class ContentContainer {

	//globals
	protected final Set<String> definedGlobals;
	protected final Set<String> referencedGlobals;

	//variables
	protected final Set<String> definedVariables;
	protected final Set<String> referencedVariables;
	protected final Map<String, Set<String>> assigns;

	//methods
	protected final Map<String, Method> definedMethods;
	protected final Set<String> calledMethods;

	//children
	protected final Set<ContentContainer> children;

	public ContentContainer() {
		this.definedGlobals = new HashSet<>();
		this.referencedGlobals = new HashSet<>();

		this.definedVariables = new HashSet<>();
		this.referencedVariables = new HashSet<>();
		this.assigns = new HashMap<>();

		this.definedMethods = new HashMap<>();
		this.calledMethods = new HashSet<>();

		this.children = new HashSet<>();
	}

	public void addGlobalDefinition(String name) {
		this.definedGlobals.add(name);
	}

	public void addGlobalReference(String name) {
		this.referencedGlobals.add(name);
	}

	public void addVariableDefinition(String name) {
		this.definedVariables.add(name);
	}

	public void addVariableReference(String name) {
		this.referencedVariables.add(name);
	}

	public void addAssign(String target, String source) {
		if (!this.assigns.containsKey(target)) {
			this.assigns.put(target, new HashSet<>());
		}
		this.assigns.get(target).add(source);
	}

	public void addMethodDefinition(Method method) {
		this.definedMethods.put(method.getName(), method);
	}

	public void addMethodCall(String methodName) {
		this.calledMethods.add(methodName);
	}

	public void addChild(ContentContainer child) {
		this.children.add(child);
	}

	public Set<String> getDefinedGlobals() {
		return this.definedGlobals;
	}

	public Set<String> getReferencedGlobals() {
		return this.referencedGlobals;
	}

	public Set<String> getDefinedVariables() {
		return this.definedVariables;
	}

	public Set<String> getReferencedVariables() {
		return this.referencedVariables;
	}

	public Map<String, Set<String>> getAssigns() {
		return this.assigns;
	}

	public Set<Method> getMethods() {
		return this.definedMethods.values().stream().collect(Collectors.toSet());
	}

	public Map<String, Method> getMethodsByName() {
		return this.definedMethods;
	}

	public Integer publicVariablesCount() {
		Long count = this.definedVariables.stream()
				.filter(v -> !this.isPrivateVariable(v)).count();
		return count.intValue();
	}

	public Integer privateVariablesCount() {
		Long count = this.definedVariables.stream()
				.filter(v -> this.isPrivateVariable(v)).count();
		return count.intValue();
	}

	private Boolean isPrivateVariable(String varName) {
		return varName.startsWith("_");
	}

	public Integer methodCount() {
		return this.definedMethods.size();
	}

	public Integer publicMethodCount() {
		Long l = this.definedMethods.values().stream()
				.filter(Method::isPrivate)
				.count();
		return l.intValue();
	}

	public Integer accessorsCount() {
		Long c = this.definedMethods.values().stream()
				.filter(Method::isAccessor)
				.count();
		return c.intValue();
	}

	public Integer definedGlobalsCount() {
		return this.definedGlobals.size();
	}

	public Integer definedVariablesCount() {
		return this.definedVariables.size();
	}

	public Set<String> getCalledMethods() {
		return this.calledMethods;
	}

	public Boolean hasNoMethods() {
		return this.definedMethods.size() == 0;
	}


	public Boolean usesGlobals() {
		return this.referencedGlobals.size() > 0;
	}

	public Integer referencedGlobalsCount() {
		return this.referencedGlobals.size();
	}

	public Integer methodsWithNoParamsCount() {
		Long count = this.definedMethods.values().stream()
				.filter(m -> m.paramCount() == 0)
				.count();
		return count.intValue();
	}
}
