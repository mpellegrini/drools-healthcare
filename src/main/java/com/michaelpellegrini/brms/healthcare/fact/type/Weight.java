package com.michaelpellegrini.brms.healthcare.fact.type;

import javax.measure.Measurable;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Weight {

	private Measurable<Mass> value;
	
	public Weight(Measurable<Mass> value) {
		if (value.doubleValue(SI.KILOGRAM) <= 0) {
			throw new IllegalArgumentException("Weight must be greater than zero");
		}
		
		this.value = value;
	}

	public Measurable<Mass> getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
}
