package com.company.models;

import com.company.factories.BookFactory;

public class Book extends Publication{
    private String author;
    private String genre;
    private int numberOfPages;

    public Book(String name, String author, String genre, int numberOfPages) {
        super(name);
        this.author = author;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    public Book(Book book) {
        super(book.getName());
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.numberOfPages = book.getNumberOfPages();
    }

    public Book(){
        this(BookFactory.getRandomBook());
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Number of pages: " + numberOfPages);
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public String getAuthor() {
        return author;
    }
    public String getGenre() {
        return genre;
    }
    public int getNumberOfPages() {
        return numberOfPages;
    }
}
