package com.company.factories;

import com.company.model.Book;
import com.company.util.RandomElements;

public class BookFactory {
    public static final String[] names = {"To Kill a Mockingbird", "1984", "The Catcher in the Rye", "The Great Gatsby", "Moby Dick"};
    public static final String[] authorNames = {"Harper", "George", "J.D.", "F. Scott", "Herman"};
    public static final String[] authorSurnames = {"Lee", "Orwell", "Salinger", "Fitzgerald", "Melville"};
    public static final String[] authorPatronymics = {"Nelle", "Eric Arthur", "Jerome David", "Key", "Noah"};
    public static final int[] years = {1960, 1949, 1951, 1925, 1851};
    public static final String[] publishers = {"J.B. Lippincott & Co.", "Secker & Warburg", "Little, Brown and Company", "Scribner", "Harper & Brothers"};
    public static final String[] genres = {"Southern Gothic", "Dystopian", "Realistic Fiction", "Novel", "Adventure"};
    public static final int[] numberOfPages = {281, 328, 277, 218, 635};

    public static Book getRandomBook() {
        return new Book(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(authorNames),
                RandomElements.getRandomElement(authorSurnames),
                RandomElements.getRandomElement(authorPatronymics),
                RandomElements.getRandomElement(years),
                RandomElements.getRandomElement(publishers),
                RandomElements.getRandomElement(genres),
                RandomElements.getRandomElement(numberOfPages)
        );
    }
}
