package thesis;

import java.util.*;

/**
 * Created by Nik on 08-05-2015
 */
public class DependencyGraph implements Iterable<String> {

	private final Map<String, Set<String>> graph;

	public DependencyGraph() {
		this.graph = new HashMap<>();
	}

	public void init(String dependant) {
		assert (!this.graph.containsKey(dependant));
		this.graph.put(dependant, new HashSet<>());
	}

	public void add(String dependant, String target) {
		assert (this.graph.containsKey(dependant));
		this.graph.get(dependant).add(target);
	}

	public Set<String> getDependencies(String dependant) {
		assert (this.graph.containsKey(dependant));
		return this.graph.get(dependant);
	}


	public void findCyclicDependencies() {
		Stack<String> currentPath = new Stack<>();

		List<String> resolved = new ArrayList<>();
		List<String> unresolved = new ArrayList<>();
		unresolved.addAll(this.graph.keySet());

		while (!unresolved.isEmpty()) {
			if (this.classContainsCycles(unresolved.get(0), currentPath, resolved, unresolved)) {
				List<String> currentPathList = new ArrayList<>();
				while (!currentPath.isEmpty()) {
					currentPathList.add(currentPath.pop());
				}

				System.out.println("\nCyclic Dependencies: ");
				currentPathList.forEach(this::printDependency);
			}
		}
	}

	private boolean classContainsCycles(String className, Stack<String> currentPath, List<String> resolved, List<String> unresolved) {
		Set<String> dependencies = this.getDependencies(className);

		currentPath.push(className);

		for (String dependency : dependencies) {
			if (currentPath.contains(dependency)) {
				return true;
			}
			this.classContainsCycles(dependency, currentPath, resolved, unresolved);
		}

		if (unresolved.contains(className)) {
			unresolved.remove(className);
			resolved.add(className);
		}

		currentPath.pop();

		return false;
	}

	@Override
	public Iterator<String> iterator() {
		return this.graph.keySet().iterator();
	}


	public void print() {
		for (String key : this.graph.keySet()) {
			System.out.print(key);
			System.out.print(": \n");
			this.graph.get(key).forEach(this::printDependency);
		}
		System.out.println("");
	}

	public void printAlphabetically() {

		List<String> keys = this.sortSet(this.graph.keySet());

		for (String key : keys) {
			System.out.print(key);
			System.out.print(": \n");

			Set<String> values = this.graph.get(key);
			List<String> sortedValues = this.sortSet(values);

			sortedValues.forEach(this::printDependency);
		}
	}

	private List<String> sortSet(Set<String> values) {
		List<String> keys = new ArrayList<>();
		keys.addAll(values);
		Collections.sort(keys);
		return keys;
	}

	private void printDependency(String v) {
		System.out.print("\t");
		System.out.print(v);
		System.out.print("\n");
	}
}
