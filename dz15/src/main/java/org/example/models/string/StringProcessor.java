package org.example.models.string;

public class StringProcessor {
    public boolean isPalindrome(String input) {
        String cleanedInput = input.replaceAll("\\s+", "").toLowerCase();
        String reversedInput = new StringBuilder(cleanedInput).reverse().toString();
        return cleanedInput.equals(reversedInput);
    }

    public int countVowels(String input) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : input.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public int countConsonants(String input) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c) && vowels.indexOf(c) == -1) {
                count++;
            }
        }
        return count;
    }

    public int countWordOccurrences(String text, String word) {
        String cleanedText = text.replaceAll("[^a-zA-Z\\s]", "");
        String[] words = cleanedText.split("\\s+");
        int count = 0;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }
}
