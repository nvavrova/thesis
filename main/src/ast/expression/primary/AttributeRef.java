package ast.expression.primary;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

/**
 * Created by Nik on 07-07-2015
 */
public class AttributeRef extends TrailedAtom {

	private final Identifier attribute;

	public AttributeRef(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull Identifier attribute) {
		super(locInfo, base);
		this.attribute = attribute;
	}

	public Identifier getAttribute() {
		return this.attribute;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.getBase().toString() + "." + this.attribute.toString();
	}
}
