package org.example.models.length;

import org.example.enums.LengthUnit;

public class LengthConverter {
    public double convert(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double valueInMillimeters = value * fromUnit.getToMillimeterFactor();
        return valueInMillimeters / toUnit.getToMillimeterFactor();
    }
}
