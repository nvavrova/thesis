package ast.expression.unary;

/**
 * Created by Nik on 30-06-2015
 */
public interface UnaryVisitor<T> {
	T visit(Invert n);
	T visit(Minus n);
	T visit(Plus n);
}
