package ast.expression.comprehension;

/**
 * Created by Nik on 30-06-2015
 */
public interface ComprehensionVisitor<T> {
	T visit(CompFor n);
	T visit(CompIf n);
	T visit(CondComprehension n);
	T visit(EnumComprehension n);
}
