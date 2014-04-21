package com.michaelpellegrini.drools.healthcare.fact.type;

import com.michaelpellegrini.drools.healthcare.fact.value.BMIWeightCategoryConstraint;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        final BMIWeightCategory that = (BMIWeightCategory) obj;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}


