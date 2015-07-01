package thesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 30-06-2015
 */
public class Class {
	private Integer loc;
	private Boolean isController;
	private Boolean hasControllerMethods;
	private Map<String, Integer> methodLoc;
	private List<String> attributes;
	private Map<String, Integer> accessors;

	public Class(Integer loc, Boolean isController) {
		this.loc = loc;
		this.isController = isController;
		this.hasControllerMethods = false;
		this.methodLoc = new HashMap<>();
		this.attributes = new ArrayList<>();
		this.accessors = new HashMap<>();
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

	public void addAttribute(String name) {
		this.attributes.add(name);
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

	public Integer getNumberOfAttributes() {
		return this.attributes.size();
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

}
