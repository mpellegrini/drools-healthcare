package com.michaelpellegrini.brms.healthcare.fact.type;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.michaelpellegrini.brms.healthcare.fact.value.BMIDiseaseRiskConstraint;


public class BMIDiseaseRisk {
	
	private final BMIDiseaseRiskConstraint value;
	
	public BMIDiseaseRisk(BMIDiseaseRiskConstraint value) {
		this.value = value;
	}
	
	public BMIDiseaseRiskConstraint getValue() {
		return value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).toString();
	}
	
	
	
}


