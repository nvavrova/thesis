package model;

/**
 * Created by Nik on 23-10-2015
 */
public class Assign extends Instruction {

	private final String leftElement;
	private final String rightElement;
	private final AssignType type;

	public Assign(Integer order, String leftElement, String rightElement, AssignType type) {
		super(order);
		this.leftElement = leftElement;
		this.rightElement = rightElement;
		this.type = type;
	}

	public String getLeftElement() {
		return this.leftElement;
	}

	public String getRightElement() {
		return this.rightElement;
	}

	public AssignType getType() {
		return this.type;
	}
}
