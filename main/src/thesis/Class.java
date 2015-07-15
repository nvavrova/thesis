package thesis;

import ast.LocInfo;
import ast.arg.SimpleArg;

import java.util.*;

/**
 * Created by Nik on 30-06-2015
 */
//TODO: decide on the constants, limits, etc.
public class Class {

	private final String name;
	private final LocInfo loc;

	public List<SimpleArg> getOldparents() {
		return this.oldparents;
	}

	private final List<Class> parents;
	private final List<SimpleArg> oldparents;
	private final Set<String> variables;
	private final List<Method> methods;
	private final Map<String, Integer> methodPosition;
	private Boolean usesGlobals;

	public Class(String name, LocInfo loc, List<SimpleArg> oldparents) {
		this.name = name;
		this.loc = loc;
		this.parents = new ArrayList<>();
		this.oldparents = oldparents;
		this.variables = new HashSet<>();
		this.methods = new ArrayList<>();
		this.methodPosition = new HashMap<>();
		this.usesGlobals = false;
	}

	public void addParent(Class c) {
		this.parents.add(c);
	}

	public void addAccessor(String name, LocInfo loc, List<String> params) {
		this.addMethod(name, loc, params, true);
	}

	public void addMethod(String name, LocInfo loc, List<String> params) {
		this.addMethod(name, loc, params, false);
	}

	public Boolean hasNoMethods() {
		return this.methods.size() == 0;
	}

	public Method getLastAddedMethod() {
		return this.methods.get(this.methods.size() - 1);
	}

	public void addVariable(String varName) {
		this.variables.add(varName);
	}

	public void addVariable(String varName, String methodName) {
		Method method = this.getMethod(methodName);
		method.addVarUse(varName);
		this.addVariable(varName);
	}

	public String getName() {
		return this.name;
	}

	public Boolean isBlob() {
		//TODO: add data class association?
		return (this.isLargeClass() || this.hasLowCohesion()) &&
				(this.hasControllerName() || this.hasControllerMethods());
	}

	public Boolean isSwissArmyKnife() {
		return this.hasTooManyParents();
	}

	public Boolean isFunctionalDecomposition() {
		return this.hasProceduralName() && this.noInheritance(); //TODO
	}

	public Boolean isSpaghettiCode() {
		return this.noInheritance() && this.hasProceduralName() && this.hasLongMethod()
				&& this.hasTooManyMethodsWithNoParams() && this.usesGlobals();
	}

	public Integer getLoc() {
		return this.loc.getLocSpan();
	}

	public Integer getNumberOfAccessors() {
		Long count = this.methods.stream()
				.filter(m -> m.isAccessor())
				.count();
		return count.intValue();
	}

	public Set<String> getVariables() {
		return this.variables;
	}

	public String getFilePath() {
		return this.loc.getFilePath();
	}

	public Boolean usesGlobals() {
		return this.usesGlobals;
	}

	public void registerGlobalUse() {
		this.usesGlobals = true;
	}

	public Boolean hasLowCohesion() {
		return this.calculateLcom() > 5;
	}

	public Boolean isLargeClass() {
		return this.loc.getLocSpan() > 800;
	}

	public Boolean isDataClass() {
		return this.getNumberOfAccessors() > 10;
	}

	public Boolean hasControllerName() {
		return Helper.isControllerName(this.name);
	}

	public Boolean hasProceduralName() {
		return Helper.isProceduralName(this.name);
	}

	public Boolean hasControllerMethods() {
		for (Method m : this.methods) {
			if (m.isController()) {
				return true;
			}
		}
		return false;
	}

	public Boolean noInheritance() {
		return this.oldparents.size() == 0;
	}

	public Boolean lowAmountOfMethods() {
		return this.methods.size() <= 4;
	}

	public Boolean hasTooManyParents() {
		return this.oldparents.size() > 2;
	}

	public Boolean hasLongMethod() {
		for (Method m : this.methods) {
			if (m.isLongMethod()) {
				return true;
			}
		}
		return false;
	}

	public Integer getNrOfMethodsWithNoParams() {
		Long count = this.methods.stream()
				.filter(e -> e.hasNoParams())
				.count();
		return count.intValue();
	}

	public Boolean hasTooManyMethodsWithNoParams() {
		return this.getNrOfMethodsWithNoParams() > 8;
	}

	private void addMethod(String name, LocInfo loc, List<String> params, Boolean isAccessor) {
		Method method = new Method(name, loc, params, isAccessor);
		this.methodPosition.put(name, this.methods.size());
		this.methods.add(method);
	}

	private Method getMethod(String name) {
		assert (this.methodPosition.containsKey(name));

		Integer pos = this.methodPosition.get(name);
		return this.methods.get(pos);
	}

	private Integer calculateLcom() {
		int intersect = 0;
		int nonIntersect = 0;
		for (int i = 0; i < this.methods.size(); i++) {
			for (int j = i + 1; j > i && j < methods.size(); j++) {
				Method a = this.methods.get(i);
				Method b = this.methods.get(j);
				if (a.hasVariableIntersection(b)) {
					intersect++;
				}
				else {
					nonIntersect++;
				}
			}
		}
		return Math.max(0, nonIntersect - intersect);
	}
}
