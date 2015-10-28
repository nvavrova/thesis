package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nik on 30-06-2015
 */
public class Class extends ContentContainer {

	private final Integer loc;
	private final List<String> parents;
	private final Map<String, Class> dependentOn;

	public Class(String name, Integer loc, List<String> parents) {
		super(name);
		this.loc = loc;
		this.parents = parents;
		this.dependentOn = new HashMap<>();
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

	public List<String> getParents() {
		return this.parents;
	}

	public Integer parentsCount() {
		return this.parents.size();
	}

	private Boolean privateFieldsWithOnePublicMethod() {
		return //this.privateVariablesCount() > 10 &&
				this.getDefinedSubroutines().stream().filter(s -> !s.isPrivate()).count() == 1;
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
	public void resolveDependencies(Scope scope) {
		super.resolveDependencies(scope);
		for (String clsName : this.parents) {
			if (scope.definedClasses.containsKey(clsName)) {
				this.referencedClasses.put(clsName, scope.definedClasses.get(clsName));
			}
		}
	}
}
