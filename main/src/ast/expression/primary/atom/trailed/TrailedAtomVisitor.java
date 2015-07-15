package ast.expression.primary.atom.trailed;

/**
 * Created by Nik on 14-07-2015
 */
public interface TrailedAtomVisitor<T> {
	T visit(AttributeRef n);
	T visit(Call n);
	T visit(DirectCall n);
	T visit(Slice n);
	T visit(Subscription n);
}
