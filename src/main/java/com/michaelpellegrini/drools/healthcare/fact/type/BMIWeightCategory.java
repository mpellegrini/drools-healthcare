package com.michaelpellegrini.drools.healthcare.fact.type;

import com.michaelpellegrini.drools.healthcare.fact.value.BMIWeightCategoryConstraint;

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
        return new StringBuilder(this.getClass().getSimpleName())
                .append("@")
                .append(Integer.toHexString(System.identityHashCode(this)))
                .append("{")
                .append("value=").append(value)
                .append("}").toString();
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


