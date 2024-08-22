package com.company.factories;

import com.company.models.*;
import com.company.util.RandomElements;

import static com.company.factories.BookFactory.authors;

public class AlmanacFactory {
    public static final String[] names = {"Lord of The Rings", "War and Peace", "Harry Potter"};
    public static final Book[] genres = {BookFactory.getRandomBook(), BookFactory.getRandomBook(), BookFactory.getRandomBook()};

    public static Almanac getRandomAlmanac() {
        return new Almanac(
                RandomElements.getRandomElement(names),
                genres
        );
    }
}
