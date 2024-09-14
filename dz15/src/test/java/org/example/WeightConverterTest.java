package org.example;

import org.example.enums.WeightUnit;
import org.example.models.weight.WeightConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeightConverterTest {
    WeightConverter converter = new WeightConverter();

    @Test
    public void testMilligramsToGrams() {
        double result = converter.convert(1000.0, WeightUnit.MILLIGRAM, WeightUnit.GRAM);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testGramsToKilograms() {
        double result = converter.convert(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testKilogramsToTons() {
        double result = converter.convert(1000.0, WeightUnit.KILOGRAM, WeightUnit.TON);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testTonsToQuintals() {
        double result = converter.convert(1.0, WeightUnit.TON, WeightUnit.QUINTAL);
        assertEquals(10.0, result, 0.001);
    }

    @Test
    public void testQuintalsToMilligrams() {
        double result = converter.convert(1.0, WeightUnit.QUINTAL, WeightUnit.MILLIGRAM);
        assertEquals(100000000.0, result, 0.001);
    }
}
