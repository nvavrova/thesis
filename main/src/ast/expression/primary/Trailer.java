package ast.expression.primary;

/**
 * Created by Nik on 07-07-2015
 */
public interface Trailer {
	Boolean isCall();
	Boolean isAttribute();
	Boolean isSubscript();
	<T> T accept(TrailerVisitor<T> visitor);
}
