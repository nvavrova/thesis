package ast.expression.logical;

/**
 * Created by Nik on 30-06-2015
 */
public interface LogicalVisitor<T> {
	T visit(And n);
	T visit(Not n);
	T visit(Or n);
}
