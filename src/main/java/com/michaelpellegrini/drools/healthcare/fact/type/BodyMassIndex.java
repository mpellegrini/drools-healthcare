package com.michaelpellegrini.drools.healthcare.fact.type;

import java.util.Objects;

public final class BodyMassIndex {

	private final double value;
	
	public BodyMassIndex(double value) {
		this.value = value;
	}

	public double getValue() {
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

        final BodyMassIndex that = (BodyMassIndex) obj;

        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}
