package model;

/**
 * Created by Nik on 23-10-2015
 */
public class Reference extends Instruction {

	private final String value;

	public Reference(Integer order, String value) {
		super(order);
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
