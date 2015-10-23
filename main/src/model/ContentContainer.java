package model;

import java.util.*;
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

	//methods
	protected final Map<String, Method> definedMethods;
	protected final Set<String> calledMethods;

	//classes
	protected final Map<String, Class> definedClasses;
	protected final Set<String> referencedClasses;

	protected final Map<String, Set<String>> assignVars;
	protected final Map<String, Set<String>> assignCalls;

	public ContentContainer() {
		this.definedGlobals = new HashSet<>();
		this.referencedGlobals = new HashSet<>();

		this.definedVariables = new HashSet<>();
		this.referencedVariables = new HashSet<>();

		this.definedMethods = new HashMap<>();
		this.calledMethods = new HashSet<>();

		this.definedClasses = new HashMap<>();
		this.referencedClasses = new HashSet<>();

		this.assignVars = new HashMap<>();
		this.assignCalls = new HashMap<>();
	}

	public void addGlobalDefinition(String globalName) {
		this.definedGlobals.add(globalName);
	}

	public void addGlobalReference(String globalName) {
		this.referencedGlobals.add(globalName);
	}

	public void addVariableDefinition(String varName) {
		this.definedVariables.add(varName);
	}

	public void addVariableReference(String varName) {
		this.referencedVariables.add(varName);
	}

	public void addAssign(String target, String source) {
		Map<String, Set<String>> assignMap = this.referencedVariables.contains(source) ? this.assignVars : this.assignCalls;
		if (!assignMap.containsKey(target)) {
			assignMap.put(target, new HashSet<>());
		}
		assignMap.get(target).add(source);
	}

	public void addMethodDefinition(Method method) {
		this.definedMethods.put(method.getName(), method);
	}

	public void addMethodCall(String methodName) {
		this.calledMethods.add(methodName);
	}

	public void addClassDefinition(Class cls) {
		this.definedClasses.put(cls.getName(), cls);
	}

	public abstract String getName();
	public abstract Module getModule();

	public Set<String> getDefinedGlobals() {
		return this.definedGlobals;
	}

	public Set<String> getReferencedGlobals() {
		Set<String> r = new HashSet<>();
		r.addAll(this.referencedGlobals);
		this.getChildren().forEach(c -> r.addAll(c.getReferencedGlobals()));
		return r;
	}

	public Set<String> getDefinedVariables() {
		return this.definedVariables;
	}

	public Set<String> getReferencedVariables() {
		Set<String> r = new HashSet<>();
		r.addAll(this.referencedVariables);
		this.getChildren().forEach(c -> r.addAll(c.getReferencedVariables()));
		return r;
	}

	public Map<String, Set<String>> getAssignVars() {
		return this.assignVars;
	}

	public Map<String, Set<String>> getAssignCalls() {
		return this.assignCalls;
	}

	public Set<Method> getDefinedMethods() {
		return this.getDefinedMethodsByName().values().stream().collect(Collectors.toSet());
	}

	public Map<String, Method> getDefinedMethodsByName() {
		Map<String, Method> methods = new HashMap<>();
		methods.putAll(this.definedMethods);
		for (ContentContainer cc : this.getChildren()) {
			Map<String, Method> childClasses = cc.getDefinedMethodsByName();
			for (String key : childClasses.keySet()) {
				methods.put(cc.getName() + '.' + key, childClasses.get(key));
			}
		}
		return methods;
	}

	public Set<String> getCalledMethods() {
		Set<String> r = new HashSet<>();
		r.addAll(this.calledMethods);
		this.getChildren().forEach(c -> r.addAll(c.getCalledMethods()));
		return r;
	}

	public Set<Class> getDefinedClasses() {
		return this.getDefinedClassesByName().values().stream().collect(Collectors.toSet());
	}

	public Map<String, Class> getDefinedClassesByName() {
		Map<String, Class> classes = new HashMap<>();
		classes.putAll(this.definedClasses);
		for (ContentContainer cc : this.getChildren()) {
			Map<String, Class> childClasses = cc.getDefinedClassesByName();
			for (String key : childClasses.keySet()) {
				classes.put(cc.getName() + '.' + key, childClasses.get(key));
			}
		}
		return classes;
	}

	public Set<String> getReferencedClasses() {
		return this.referencedClasses;
	}

	public Set<ContentContainer> getChildren() {
		Set<ContentContainer> children = new HashSet<>();
		children.addAll(this.definedClasses.values());
		children.addAll(this.definedMethods.values());
		return children;
	}

	public void resolveGlobalReferences(Set<String> globals) {
		for (String global : globals) {
			if (this.referencedVariables.contains(global)) {
				this.referencedGlobals.add(global);
			}
		}
		this.getChildren().forEach(c -> c.resolveGlobalReferences(globals));
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
