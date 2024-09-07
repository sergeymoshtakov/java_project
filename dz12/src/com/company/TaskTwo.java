package com.company;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskTwo {
    public static void main(String[] args){
        File directory = new File("taskTwo");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file1 = new File(directory, "file1.txt");
        try (Writer bufferedWriter = new BufferedWriter(new FileWriter(file1))) {
            bufferedWriter.write("Hello, world!\n");
            bufferedWriter.write("This is a test file.\n");
            bufferedWriter.write("Java programming is fun.\n");
            bufferedWriter.write("Have a great day!\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))){
            List<String> lines = bufferedReader.lines().toList();
            Optional<String> longestLine = lines.stream()
                    .max(Comparator.comparingInt(String::length));

            if (longestLine.isPresent()) {
                String line = longestLine.get();
                System.out.println("Length of the longest line: " + line.length());
                System.out.println("Longest line: " + line);
            } else {
                System.out.println("The file is empty.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
