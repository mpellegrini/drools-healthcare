package com.michaelpellegrini.drools.healthcare.fact.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.measure.Measurable;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        final Weight that = (Weight) obj;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
