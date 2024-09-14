package org.example;

import org.example.enums.LengthUnit;
import org.example.models.length.LengthConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthConverterTest {
    LengthConverter converter = new LengthConverter();

    @Test
    public void testMillimetersToCentimeters() {
        double result = converter.convert(10.0, LengthUnit.MILLIMETER, LengthUnit.CENTIMETER);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testMetersToKilometers() {
        double result = converter.convert(1000.0, LengthUnit.METER, LengthUnit.KILOMETER);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testCentimetersToMeters() {
        double result = converter.convert(100.0, LengthUnit.CENTIMETER, LengthUnit.METER);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testKilometersToMillimeters() {
        double result = converter.convert(1.0, LengthUnit.KILOMETER, LengthUnit.MILLIMETER);
        assertEquals(1_000_000.0, result, 0.001);
    }
}
