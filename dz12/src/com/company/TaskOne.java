package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class TaskOne {
    public static void main(String[] args) {
        File directory = new File("taskOne");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File file1 = new File(directory, "file1.txt");
        File file2 = new File(directory, "file2.txt");

        try (Writer writer1 = new BufferedWriter(new FileWriter(file1));
             Writer writer2 = new BufferedWriter(new FileWriter(file2))) {

            writer1.write("Hello, world!\n");
            writer1.write("This is a test file.\n");
            writer1.write("Java programming is fun.\n");
            writer1.write("Have a great day!\n");

            writer2.write("Hello, world!\n");
            writer2.write("This is a test file.\n");
            writer2.write("Java programming is awesome.\n");
            writer2.write("Have a nice day!\n");
            writer2.write("See you soon!\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            Set<String> linesFile1 = new HashSet<>();
            Set<String> linesFile2 = new HashSet<>();
            Set<String> uniqueLinesFile1 = new HashSet<>();
            Set<String> uniqueLinesFile2 = new HashSet<>();

            String line;
            while ((line = reader1.readLine()) != null) {
                linesFile1.add(line);
            }

            while ((line = reader2.readLine()) != null) {
                linesFile2.add(line);
            }

            for (String l : linesFile1) {
                if (!linesFile2.contains(l)) {
                    uniqueLinesFile1.add(l);
                }
            }

            for (String l : linesFile2) {
                if (!linesFile1.contains(l)) {
                    uniqueLinesFile2.add(l);
                }
            }

            System.out.println("Unique lines in file1.txt:");
            for (String l : uniqueLinesFile1) {
                System.out.println(l);
            }

            System.out.println("\nUnique lines in file2.txt:");
            for (String l : uniqueLinesFile2) {
                System.out.println(l);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
