package thesis;

import org.python.antlr.PythonTree;

import java.util.Collection;

/**
 * Created by Nik on 17-05-2015
 */
public abstract class DataCollector {

	protected final Collection<PythonTree> trees;
	protected final PyVisitorExceptionHandler exceptionHandler;

	public DataCollector(Collection<PythonTree> trees, PyVisitorExceptionHandler exceptionHandler) {
		this.trees = trees;
		this.exceptionHandler = exceptionHandler;
	}
}
