package com.company.factories;

import com.company.model.Fraction;
import com.company.util.RandomElements;

public class FractionFactory {
    public static final int[] numerators = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int[] denominators = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static Fraction getRandomFraction() {
        return new Fraction(
                RandomElements.getRandomElement(numerators),
                RandomElements.getRandomElement(denominators)
        );
    }
}
