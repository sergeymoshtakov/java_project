package org.example;

import org.example.enums.CurrencyCourse;
import org.example.models.converter.CurrencyConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterTest {
    CurrencyConverter converter = new CurrencyConverter();

    @Test
    public void testUsdToEur() {
        double result = converter.convert(CurrencyCourse.USD, CurrencyCourse.EUR, 100);
        assertEquals(90.0, result, 0.001);
    }

    @Test
    public void testEurToUsd() {
        double result = converter.convert(CurrencyCourse.EUR, CurrencyCourse.USD, 100);
        assertEquals(111.11, result, 0.01);
    }

    @Test
    public void testGbpToJpy() {
        double result = converter.convert(CurrencyCourse.GBP, CurrencyCourse.JPY, 100);
        assertEquals(14473.68, result, 0.01);
    }

    @Test
    public void testJpyToEur() {
        double result = converter.convert(CurrencyCourse.JPY, CurrencyCourse.EUR, 10000);
        assertEquals(81.82, result, 0.01);
    }

    @Test
    public void testUsdToGbp() {
        double result = converter.convert(CurrencyCourse.USD, CurrencyCourse.GBP, 100);
        assertEquals(76.0, result, 0.001);
    }

    @Test
    public void testZeroAmount() {
        double result = converter.convert(CurrencyCourse.USD, CurrencyCourse.EUR, 0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testNegativeAmount() {
        double result = converter.convert(CurrencyCourse.GBP, CurrencyCourse.USD, -100);
        assertEquals(-131.58, result, 0.01);
    }
}
