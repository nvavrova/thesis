package thesis;

import ast.LocInfo;
import ast.arg.Arg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 30-06-2015
 */
//TODO: decide on the constants, limits, etc.
public class Class {
	private final static Set<String> NO_OCCURRENCE = Collections.emptySet();

	private final String name;
	private final LocInfo loc;
	private final Boolean isController;
	private Boolean hasControllerMethods;
	private final Map<String, Integer> methodLoc;
	private final Map<String, Integer> accessors;
	private final List<Arg> parents;
	private final Set<String> variables;
	private final Map<String, Set<String>> varOccurrence;

	public Class(String name, LocInfo loc, Boolean isController, List<Arg> parents) {
		this.name = name;
		this.loc = loc;
		this.isController = isController;
		this.hasControllerMethods = false;
		this.methodLoc = new HashMap<>();
		this.accessors = new HashMap<>();
		this.parents = parents;
		this.variables = new HashSet<>();
		this.varOccurrence = new HashMap<>();
	}

	public void addAccessor(String name, Integer loc) {
		this.accessors.put(name, loc);
	}

	public void addMethod(String name, Integer loc, Boolean isController) {
		if (isController) {
			this.hasControllerMethods = true;
		}
		this.methodLoc.put(name, loc);
	}

	public void addVariable(String varName) {
		this.variables.add(varName);
	}

	public void addVariable(String varName, String methodName) {
		if (!this.varOccurrence.containsKey(methodName)) {
			this.varOccurrence.put(methodName, new HashSet<>());
		}
		this.varOccurrence.get(methodName).add(varName);
		this.addVariable(varName);
	}

	public String getName() {
		return this.name;
	}

	public Integer getLoc() {
		return this.loc.getLocSpan();
	}

	public Integer getNumberOfMethods() {
		return this.methodLoc.size() + this.accessors.size();
	}

	public Integer getNumberOfAccessors() {
		return this.accessors.size();
	}

	public Set<String> getVariables() {
		return this.variables;
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

	public Boolean isController() {
		return this.isController;
	}

	public Boolean hasControllerMethods() {
		return this.hasControllerMethods;
	}

	public Boolean isBlob() {
		//TODO: add data class association
		return (this.isLargeClass() || this.hasLowCohesion()) && (this.isController() || this.hasControllerMethods());
	}

	public Boolean hasMultipleInheritance() {
		return this.parents.size() > 1;
	}

	private Integer calculateLcom() {
		List<String> methods = this.methodLoc.keySet().stream().collect(Collectors.toList());
		int intersect = 0;
		int nonIntersect = 0;
		for (int i = 0; i < methods.size(); i++) {
			for (int j = i + 1; j > i && j < methods.size(); j++) {
				Set<String> varsA = this.getVarOccurrenceForMethod(methods.get(i));
				Set<String> varsB = this.getVarOccurrenceForMethod(methods.get(j));
				if (Helper.areSetsDisjoint(varsA, varsB)) {
					nonIntersect++;
				}
				else {
					intersect++;
				}
			}
		}
		return Math.max(0, nonIntersect - intersect);
	}

	private Set<String> getVarOccurrenceForMethod(String methodName) {
		if (this.varOccurrence.containsKey(methodName)) {
			return this.varOccurrence.get(methodName);
		}
		return NO_OCCURRENCE;
	}

}
