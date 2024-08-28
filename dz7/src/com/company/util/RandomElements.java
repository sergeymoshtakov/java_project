package com.company.util;

public class RandomElements {
    public static int getRandomElement(int[] array) {
        return array[(int) (Math.random() * array.length)];
    }
}
