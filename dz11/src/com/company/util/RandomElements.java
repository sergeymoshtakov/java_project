package com.company.util;

import com.company.enums.Category;
import com.company.enums.Color;
import com.company.enums.DeviceType;

public class RandomElements {
    public static String getRandomElement(String[] array) {
        return array[(int) (Math.random() * array.length)];
    }

    public static Category getRandomElement(Category[] values) {
        return values[(int) (Math.random() * values.length)];
    }

    public static DeviceType getRandomElement(DeviceType[] values) {
        return values[(int) (Math.random() * values.length)];
    }

    public static Color getRandomElement(Color[] values) {
        return values[(int) (Math.random() * values.length)];
    }

    public static int getRandomElement(int[] array) {
        return array[(int) (Math.random() * array.length)];
    }

    public static double getRandomElement(double[] array) {
        return array[(int) (Math.random() * array.length)];
    }
}
