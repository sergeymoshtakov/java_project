package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskThree {
    public static void main(String[] args){
        File directory = new File("taskThree");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file1 = new File(directory, "file1.txt");

        try (Writer writer = new FileWriter(file1)) {
            writer.write("1 23 43 9\n");
            writer.write("6 33 77 88\n");
            writer.write("3 4 5 6 7\n");
            writer.write("2 5 6 7 8\n");
            writer.write("1 2 3 4\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            List<String> strings = reader.lines().toList();
            List<int[]> arrays = new ArrayList<>();
            for (String string : strings) {
                String[] split = string.split(" ");
                int[] array = Arrays.stream(split)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                arrays.add(array);
            }

            int globalMin = arrays.get(0)[0];
            int globalMax = arrays.get(0)[0];
            int globalSum = 0;

            for (int[] array : arrays) {
                int arrayMin = Arrays.stream(array).min().orElseThrow();
                int arrayMax = Arrays.stream(array).max().orElseThrow();
                int sum = Arrays.stream(array).sum();

                globalMin = Math.min(globalMin, arrayMin);
                globalMax = Math.max(globalMax, arrayMax);
                globalSum += sum;

                System.out.println("Array: " + Arrays.toString(array));
                System.out.println("Maximum: " + arrayMax);
                System.out.println("Minimum: " + arrayMin);
                System.out.println("Sum: " + sum);
                System.out.println();
            }

            System.out.println("Global Minimum: " + globalMin);
            System.out.println("Global Maximum: " + globalMax);
            System.out.println("Global Sum: " + globalSum);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
