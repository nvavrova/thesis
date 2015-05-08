
import java.util.*;

/**
 * Created by Nik on 08-05-2015
 */
public class DependencyGraph {

	private final Map<String, Set<String>> graph;

	public DependencyGraph() {
		this.graph = new HashMap<>();
	}

	public void addDependency(String dependant, String target) {
		if (!this.graph.containsKey(dependant)) {
			this.graph.put(dependant, new HashSet<>());
		}
		this.graph.get(dependant).add(target);
	}

	public Set<String> getDependencies(String dependant) {
		if (this.graph.containsKey(dependant)) {
			return this.graph.get(dependant);
		}
		return Collections.EMPTY_SET;
	}

	public void print() {
		for (String key : this.graph.keySet()) {
			System.out.print(key);
			System.out.print(": ");
			this.graph.get(key).forEach(this::printDependency);
		}
		System.out.println("");
	}

	private void printDependency(String v) {
		System.out.print("\n\t" + v);
	}

}
