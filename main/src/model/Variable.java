package model;

/**
 * Created by Nik on 26-10-2015
 */
public class Variable {

	private final String name;
	private final ContentContainer parent;
	private final VarType varType;

	public Variable(String name, ContentContainer parent, VarType varType) {
		this.name = name;
		this.parent = parent;
		this.varType = varType;
	}

	public String getName() {
		return this.name;
	}

	public VarType getVarType() {
		return this.varType;
	}

	public boolean isProtected() {
		return this.name.startsWith("_") && !this.isPrivate();
	}

	public boolean isPrivate() {
		return this.name.startsWith("__");
	}

	public boolean isPublic() {
		return !this.isPrivate() && !this.isProtected();
	}

	public boolean definedInParentOf(ContentContainer container) {
		return container.isInParentLine(this.parent);
	}
}
