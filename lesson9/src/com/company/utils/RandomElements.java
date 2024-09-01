package com.company.utils;

public class RandomElements {
    public static int getRandomElement(int[] array) {
        return array[(int) (Math.random() * array.length)];
    }
    public static String getRandomElement(String[] array) {
        return array[(int) (Math.random() * array.length)];
    }
}
