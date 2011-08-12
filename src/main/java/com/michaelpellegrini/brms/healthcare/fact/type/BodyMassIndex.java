package com.michaelpellegrini.brms.healthcare.fact.type;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BodyMassIndex {

	private final double value;
	
	public BodyMassIndex(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
}
