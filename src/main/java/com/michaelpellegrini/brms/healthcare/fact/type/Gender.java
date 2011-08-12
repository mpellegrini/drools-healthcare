package com.michaelpellegrini.brms.healthcare.fact.type;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.michaelpellegrini.brms.healthcare.fact.value.GenderConstraint;


public class Gender {
	
	private final GenderConstraint value;
	
	public Gender(GenderConstraint value) {
		this.value = value;
	}
	
	public GenderConstraint getValue() {
		return value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
	
	
}


