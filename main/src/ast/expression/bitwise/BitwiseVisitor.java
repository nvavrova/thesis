package ast.expression.bitwise;

/**
 * Created by Nik on 30-06-2015
 */
public interface BitwiseVisitor<T> {
	T visit(And n);
	T visit(Or n);
	T visit(Xor n);
}
