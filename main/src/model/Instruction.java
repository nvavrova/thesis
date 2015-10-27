package model;

/**
 * Created by Nik on 23-10-2015
 */
public abstract class Instruction {
	protected final Integer order;

	public Instruction(Integer order) {
		this.order = order;
	}

	public Integer getOrder() {
		return this.order;
	}

	public boolean isBefore(Instruction instruction) {
		return this.order < instruction.order;
	}
}
