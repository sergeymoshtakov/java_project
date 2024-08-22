package com.company;

import com.company.models.*;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(10);
        library.initializeLibrary();

        Publication book1 = new Book("MyBook", "MyAuthor", "MyGenre", 1000);
        book1.printInfo();
        System.out.println();

        library.addPublication(book1);
        library.addPublication(new Book());
        library.printInfo();

        System.out.println();
//        library.removePublicationByName("MyBook");
//        library.printInfo();

        System.out.println("Book of MyAuthor");
        library.searchByAuthor("MyAuthor").printInfo();
    }
}