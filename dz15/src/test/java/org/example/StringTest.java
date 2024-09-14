package org.example;

import org.example.models.string.StringProcessor;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTest {
    StringProcessor processor = new StringProcessor();

    @Test
    public void testIsPalindrome() {
        assertTrue(processor.isPalindrome("A man a plan a canal Panama"));
        assertTrue(processor.isPalindrome("madam"));
        assertFalse(processor.isPalindrome("hello"));
    }

    @Test
    public void testCountVowels() {
        assertEquals(3, processor.countVowels("Hello World"));
        assertEquals(2, processor.countVowels("Java"));
        assertEquals(0, processor.countVowels("bcdfg"));
    }

    @Test
    public void testCountConsonants() {
        assertEquals(7, processor.countConsonants("Hello World"));
        assertEquals(2, processor.countConsonants("Java"));
        assertEquals(5, processor.countConsonants("bcdfg"));
    }

    @Test
    public void testCountWordOccurrences() {
        assertEquals(2, processor.countWordOccurrences("Hello world, hello again", "hello"));
        assertEquals(1, processor.countWordOccurrences("Java is great", "Java"));
        assertEquals(0, processor.countWordOccurrences("Hello world", "Python"));
    }

}
