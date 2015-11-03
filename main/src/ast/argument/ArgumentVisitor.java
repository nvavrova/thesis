package ast.argument;

/**
 * Created by Nik on 03-11-2015
 */
public interface ArgumentVisitor<T> {
	T visit(Arg n);
	T visit(CondArgument n);
	T visit(DefValArgument n);
	T visit(Kwarg n);
	T visit(SimpleArgument n);
}
