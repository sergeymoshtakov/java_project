package com.company.util;

public class RandomElements {
    public static String getRandomElement(String[] array) {
        return array[(int) (Math.random() * array.length)];
    }
    public static int getRandomElement(int[] array) {
        return array[(int) (Math.random() * array.length)];
    }
    public static double getRandomElement(double[] array) {
        return array[(int) (Math.random() * array.length)];
    }
}
