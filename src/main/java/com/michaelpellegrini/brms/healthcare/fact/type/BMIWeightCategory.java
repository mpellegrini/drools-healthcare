package com.michaelpellegrini.brms.healthcare.fact.type;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.michaelpellegrini.brms.healthcare.fact.value.BMIWeightCategoryConstraint;


public class BMIWeightCategory {
	
	private final BMIWeightCategoryConstraint value;
	
	public BMIWeightCategory(BMIWeightCategoryConstraint value) {
		this.value = value;
	}
	
	public BMIWeightCategoryConstraint getValue() {
		return value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
	
	
}


