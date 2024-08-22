package com.company.factories;


import com.company.models.*;
import com.company.util.RandomElements;

import java.time.LocalDate;

public class NewspaperFactory {
    public static final String[] names = {"Lord of The Rings", "War and Peace", "Harry Potter"};
    public static final LocalDate[] datesOfPublication = {LocalDate.of(2012, 12, 12), LocalDate.of(2011, 11, 11), LocalDate.of(2011, 12, 12)};
    public static final String[] titles = {};

    public static Newspaper getRandomNewspaper() {
        return new Newspaper(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(datesOfPublication),
                titles
        );
    }
}
