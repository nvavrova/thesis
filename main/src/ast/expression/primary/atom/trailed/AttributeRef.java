package ast.expression.primary.atom.trailed;

import ast.LocInfo;
import ast.expression.primary.atom.Atom;
import ast.expression.primary.atom.Identifier;
import org.antlr.v4.runtime.misc.NotNull;
import thesis.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik on 07-07-2015
 */
public class AttributeRef extends TrailedAtom {

	private final List<Identifier> attributes;

	public AttributeRef(@NotNull LocInfo locInfo, @NotNull AttributeRef base, @NotNull Identifier attribute) {
		super(locInfo, base.getBase());
		this.attributes = base.getAttributes();
		this.attributes.add(attribute);
	}

	public AttributeRef(@NotNull LocInfo locInfo, @NotNull Atom base, @NotNull Identifier attribute) {
		super(locInfo, base);
		this.attributes = new ArrayList<>();
		this.attributes.add(attribute);
	}

	public List<Identifier> getAttributes() {
		return this.attributes;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		List<String> parts = new ArrayList<>();
		parts.add(this.getBase().toString());
		this.attributes.forEach(a -> parts.add(a.toString()));
		return String.join(".", parts);
	}
}
