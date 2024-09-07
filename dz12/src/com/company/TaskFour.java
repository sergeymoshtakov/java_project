package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskFour {
    public static void main(String[] args) {
        File directory = new File("taskFour");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file1 = new File(directory, "file1.txt");

        int length;
        int[] array;

        try (Scanner scanner = new Scanner(System.in)) {

            do {
                System.out.print("Enter number of digits: ");
                length = scanner.nextInt();
            } while (length < 0);

             array = new int[length];

            for (int i = 0; i < length; i++) {
                System.out.print("Enter digit: ");
                array[i] = scanner.nextInt();
            }
        }

        int[] evenNumbers = Arrays.stream(array).filter(x -> (x % 2) == 0).toArray();
        int[] addNumbers = Arrays.stream(array).filter(x -> x % 2 == 1).toArray();
        List<Integer> list = new ArrayList<>(Arrays.stream(array)
                .boxed()
                .toList());
        Collections.reverse(list);
        int[] switchedNumbers = list.stream().mapToInt(Integer::intValue).toArray();

        try (Writer writer = new FileWriter(file1)) {
            writer.write(Arrays.toString(array) + "\n");
            writer.write(Arrays.toString(evenNumbers) + "\n");
            writer.write(Arrays.toString(addNumbers) + "\n");
            writer.write(Arrays.toString(switchedNumbers) + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))){
            List<String> lines = bufferedReader.lines().toList();
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
