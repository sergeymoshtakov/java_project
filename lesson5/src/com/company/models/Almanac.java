package com.company.models;

import com.company.factories.AlmanacFactory;

public class Almanac extends Publication {
    private Book[] books;

    public Almanac(String name, Book[] books) {
        super(name);
        setBooks(books);
    }

    public Almanac(Almanac almanac) {
        super(almanac.getName());
        setBooks(almanac.getBooks());
    }

    public Almanac() {
        this(AlmanacFactory.getRandomAlmanac());
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Books:");
        for(int i = 0; i < books.length; i++){
            books[i].printInfo();
        }
    }

    public void setBooks(Book[] books) {
        this.books = new Book[books.length];
        for (int i = 0; i < books.length; i++) {
            this.books[i] = new Book(books[i].getName(), books[i].getAuthor(), books[i].getGenre(), books[i].getNumberOfPages());
        }
    }
    public Book[] getBooks() {
        Book[] result = new Book[this.books.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Book(this.books[i].getName(), this.books[i].getAuthor(), this.books[i].getGenre(), this.books[i].getNumberOfPages());
        }
        return result;
    }
}
