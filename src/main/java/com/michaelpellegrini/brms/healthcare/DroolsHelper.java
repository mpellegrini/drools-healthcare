package com.michaelpellegrini.brms.healthcare;

import javax.measure.Measurable;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;

public class DroolsHelper {
	
	public static double calculateBMI(Measurable<Length> height, Measurable<Mass> weight) {
		/**
		** English BMI Formula
		** BMI = ( Weight in Pounds / ( Height in inches ) x ( Height in inches ) ) x 703
		** Metric BMI Formula
		** BMI = ( Weight in Kilograms / ( Height in Meters ) x ( Height in Meters ) )
		*/
		double bmi = weight.doubleValue(SI.KILOGRAM) / (height.doubleValue(SI.METRE) * height.doubleValue(SI.METRE));

		// Rounding double 
		float p = (float) Math.pow(10, 1);
		bmi = bmi * p;
		double tmp = Math.round(bmi);
		
		return tmp/p;
	}
}
