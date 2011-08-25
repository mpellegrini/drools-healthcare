package com.michaelpellegrini.brms.healthcare.fact.type;

import javax.measure.Measurable;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;

import org.apache.commons.lang.builder.ToStringBuilder;

public class WaistCircumference {

	private final Measurable<Length> value;
	
	public WaistCircumference(Measurable<Length> value) {
		if (value.doubleValue(SI.CENTIMETRE) <= 0) {
			throw new IllegalArgumentException("Height must be greater than zero");
		}
		
		this.value = value;
	}

	public Measurable<Length> getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
	
}
