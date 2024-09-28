package com.example.demo.util;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    public static <T> T getRandomElement(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * list.size());
        return list.get(randomIndex);
    }
}
