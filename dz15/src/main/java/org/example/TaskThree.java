package org.example;

import org.example.models.string.StringProcessor;

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringProcessor processor = new StringProcessor();

        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("Is Palindrome: " + processor.isPalindrome(input));
        System.out.println("Number of Vowels: " + processor.countVowels(input));
        System.out.println("Number of Consonants: " + processor.countConsonants(input));

        System.out.println("Enter a word to count occurrences: ");
        String word = scanner.nextLine();
        System.out.println("Occurrences of \"" + word + "\": " + processor.countWordOccurrences(input, word));
    }
}
