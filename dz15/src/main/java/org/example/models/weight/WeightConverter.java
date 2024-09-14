package org.example.models.weight;

import org.example.enums.WeightUnit;

public class WeightConverter {
    public double convert(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        double valueInMilligrams = value * fromUnit.getToMilligramFactor();
        return valueInMilligrams / toUnit.getToMilligramFactor();
    }
}
