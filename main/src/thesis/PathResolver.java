package thesis;

import java.util.List;
import java.util.Stack;

/**
 * Created by Nik on 13-07-2015
 */
public class PathResolver {

	private final static String DELIMITER = "\\";

//	private final String currentPath;
	private final List<String> explodedPath;

	public PathResolver(String currentPath) {
//		this.currentPath = currentPath;
		this.explodedPath = this.explodePath(currentPath);
	}

	public String resolve(String path) {
		Stack<String> result = new Stack<>();
		this.explodedPath.forEach(p -> result.push(p));
		result.pop();

		this.travelUpwards(path, result);

		StringBuilder sb = new StringBuilder();
		result.stream().forEach(p -> sb.append(p));

		return sb.toString();
	}

	private List<String> explodePath(String path) {
		return StringHelper.explode(path, DELIMITER);
	}

	private void travelUpwards(String path, Stack<String> currentPath) {
		List<String> pathToResolve = this.explodePath(path);
		for (int i = 0; i <= pathToResolve.size(); i++) {
			String pathPart = pathToResolve.get(i);
			if (pathPart.equals("..")) {
				currentPath.pop();
			}
			else {
				break;
			}
		}
	}
}
