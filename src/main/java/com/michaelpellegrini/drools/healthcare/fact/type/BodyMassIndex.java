package com.michaelpellegrini.drools.healthcare.fact.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        final BodyMassIndex that = (BodyMassIndex) obj;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
