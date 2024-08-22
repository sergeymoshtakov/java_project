package com.company.factories;

import com.company.models.Book;
import com.company.util.RandomElements;

public class BookFactory {
    public static final String[] names = {"Lord of The Rings", "Romeo and Juliet", "Harry Potter"};
    public static final String[] authors = {"J.R. Tolkien", "William Shakespeare", "Joan Rowling"};
    public static final String[] genres = {"Southern Gothic", "Dystopian", "Realistic Fiction", "Novel", "Adventure"};
    public static final int[] numberOfPages = {281, 328, 277, 218, 635};

    public static Book getRandomBook() {
        return new Book(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(authors),
                RandomElements.getRandomElement(genres),
                RandomElements.getRandomElement(numberOfPages)
        );
    }
}
