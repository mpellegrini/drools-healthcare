package com.michaelpellegrini.brms.healthcare.fact.type;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BloodPressureSystolic {

	private final double value;
	
	public BloodPressureSystolic(double value) {
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
