package ast.node;

import ast.node.expression.atom.Identifier;
import com.sun.deploy.util.StringUtils;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class DottedPath extends Path {

	private static final String DELIMITER = ".";
	private final List<String> path;

	public DottedPath(LocInfo locInfo, List<String> path) {
		this(locInfo, path, null);
	}

	public DottedPath(LocInfo locInfo, List<String> path, Identifier alias) {
		super(locInfo, alias);
		this.path = path;
	}

	public List<String> getPathParts() {
		return this.path;
	}

	@Override
	public String getPath() {
		return StringUtils.join(this.path, DELIMITER);
	}

	@Override
	public <T> T accept(Py3TreeVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
