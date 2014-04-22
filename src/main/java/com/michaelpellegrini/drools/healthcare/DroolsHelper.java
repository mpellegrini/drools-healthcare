package com.michaelpellegrini.drools.healthcare;

import javax.measure.Measurable;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DroolsHelper {

    /**
     * English BMI Formula
     * BMI = ( Weight in Pounds / ( Height in inches ) x ( Height in inches ) ) x 703
     *
     * Metric BMI Formula
     * BMI = ( Weight in Kilograms / ( Height in Meters ) x ( Height in Meters ) )
     *
     * @param height
     * @param weight
     * @return
     */
    public static double calculateBMI(final Measurable<Length> height, final Measurable<Mass> weight) {
        double bmi = weight.doubleValue(SI.KILOGRAM) / (height.doubleValue(SI.METRE) * height.doubleValue(SI.METRE));

        // Round double to one decimal place
        BigDecimal bigDecimal = new BigDecimal(bmi).setScale(1, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
	}
}
