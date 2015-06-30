package ast;

import com.sun.deploy.util.StringUtils;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Py3TreeVisitor;

import java.util.List;

/**
 * Created by Nik on 23-06-2015
 */
public class DottedPath extends Path {

	private static final String DELIMITER = ".";
	private final List<String> path;

	public DottedPath(LocInfo locInfo, @NotNull List<String> path) {
		super(locInfo);
		this.path = path;
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
