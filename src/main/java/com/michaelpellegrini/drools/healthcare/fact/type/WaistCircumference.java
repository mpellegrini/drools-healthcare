package com.michaelpellegrini.drools.healthcare.fact.type;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.measure.Measurable;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        final WaistCircumference that = (WaistCircumference) obj;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
