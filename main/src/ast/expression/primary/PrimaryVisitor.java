package ast.expression.primary;

/**
 * Created by Nik on 30-06-2015
 */
public interface PrimaryVisitor<T> {
	T visit(ArgList argList);
	T visit(SliceBound n);
	T visit(SubscriptIndex n);
	T visit(SubscriptList n);
}
