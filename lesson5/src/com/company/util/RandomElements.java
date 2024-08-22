package com.company.util;

import java.time.LocalDate;

public class RandomElements {
    public static String getRandomElement(String[] array) {
        return array[(int) (Math.random() * array.length)];
    }
    public static int getRandomElement(int[] array) {
        return array[(int) (Math.random() * array.length)];
    }
    public static LocalDate getRandomElement(LocalDate[] array) {
        return array[(int) (Math.random() * array.length)];
    }
}
