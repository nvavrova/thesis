package thesis;

import ast.arg.Arg;

import java.util.*;

/**
 * Created by Nik on 30-06-2015
 */
public class Class {

	private final String name;
	private final Integer loc;
	private final Boolean isController;
	private Boolean hasControllerMethods;
	private final Map<String, Integer> methodLoc;
	private final Map<String, Integer> accessors;
	private final List<Arg> parents;
	private final Set<String> variables;

	public Class(String name, Integer loc, Boolean isController, List<Arg> parents) {
		this.name = name;
		this.loc = loc;
		this.isController = isController;
		this.hasControllerMethods = false;
		this.methodLoc = new HashMap<>();
		this.accessors = new HashMap<>();
		this.parents = parents;
		this.variables = new HashSet<>();
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

	public void addVariable(String name) {
		this.variables.add(name);
	}

	public String getName() {
		return this.name;
	}

	public Integer getLoc() {
		return this.loc;
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
		//TODO
		return true;
	}

	public Boolean isLargeClass() {
		return this.loc > 800;
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

}
