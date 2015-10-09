package ast.expression.no_cond.trailer;

/**
 * Created by Nik on 07-07-2015
 */
public interface Trailer {
	<T> T accept(TrailerVisitor<T> visitor);
}
