package model;

/**
 * Created by Nik on 26-10-2015
 */
public class Variable implements Unlinkable {

	private ContentContainer parent;
	private final String name;
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

	public void unlink() {
		this.parent = null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Variable)) {
			return false;
		}

		Variable variable = (Variable) o;

		if (!this.parent.equals(variable.parent)) {
			return false;
		}
		if (!this.getName().equals(variable.getName())) {
			return false;
		}
		return this.getVarType() == variable.getVarType();

	}

	@Override
	public int hashCode() {
		int result = this.parent.hashCode();
		result = 31 * result + this.getName().hashCode();
		result = 31 * result + this.getVarType().hashCode();
		return result;
	}


}
