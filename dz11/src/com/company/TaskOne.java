package com.company;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TaskOne {
    public static void main(String[] args) {
        Random random = new Random();
        int size = 100;
        List<Integer> numbers = IntStream.generate(() -> random.nextInt(2000) - 1000).limit(size).boxed().toList();
        System.out.println(numbers);
        long positivesCount = numbers.stream().filter(n -> n > 0).count();
        long negativesCount = numbers.stream().filter(n -> n < 0).count();
        long twoDigitsCount = numbers.stream().filter(n -> n > 9 && n < 100).count();
        long palindromeCount = numbers.stream().filter(TaskOne::isPalindrome).count();
        System.out.println("Number of positives: " + positivesCount);
        System.out.println("Number of negatives: " + negativesCount);
        System.out.println("Number of two digits: " + twoDigitsCount);
        System.out.println("Number of palindromes: " + palindromeCount);
    }

    public static boolean isPalindrome(int number) {
        String str = String.valueOf(Math.abs(number));
        return IntStream.range(0, str.length() / 2).allMatch(i -> str.charAt(i) == str.charAt(str.length() - i - 1));
    }
}
