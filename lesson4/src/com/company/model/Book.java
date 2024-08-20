package com.company.model;

import com.company.factories.BookFactory;

import java.util.Scanner;

public class Book {
    private String name;
    private String authorName;
    private String authorSurname;
    private String authorPatronymic;
    private int year;
    private String publisher;
    private String genre;
    private int numberOfPages;

    public Book(String name, String authorName, String authorSurname, String authorPatronymic, int year, String publisher, String genre, int numberOfPages) {
        this.name = name;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorPatronymic = authorPatronymic;
        this.year = year;
        this.publisher = publisher;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
    }

    public Book(Book book) {
        this(book.getName(), book.getAuthorName(), book.getAuthorSurname(), book.getAuthorPatronymic(), book.getYear(), book.getPublisher(), book.getGenre(), book.getNumberOfPages());
    }

    public Book(){
        this(BookFactory.getRandomBook());
    }

    public void inputBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the book: ");
        this.setName(scanner.nextLine());
        System.out.println("Enter the author name of the book: ");
        this.setAuthorName(scanner.nextLine());
        System.out.println("Enter the author surname of the book: ");
        this.setAuthorSurname(scanner.nextLine());
        System.out.println("Enter the author patronymic of the book: ");
        this.setAuthorPatronymic(scanner.nextLine());
        System.out.println("Enter the year of the book: ");
        this.setYear(scanner.nextInt());
        System.out.println("Enter the publisher of the book: ");
        this.setPublisher(scanner.nextLine());
        System.out.println("Enter the genre of the book: ");
        this.setGenre(scanner.nextLine());
        System.out.println("Enter the number of pages of the book: ");
        this.setNumberOfPages(scanner.nextInt());
    }

    public void printBook(boolean shortFormat){
        if(shortFormat){
            System.out.println("Book name: " + this.getName());
        } else {
            printBook();
        }
    }

    public void printBook(){
        System.out.println("Book:");
        System.out.println("Name: " + this.getName());
        System.out.println("Author: " + this.getAuthorName());
        System.out.println("Author Surname: " + this.getAuthorSurname());
        System.out.println("Author Patronymic: " + this.getAuthorPatronymic());
        System.out.println("Year: " + this.getYear());
        System.out.println("Publisher: " + this.getPublisher());
        System.out.println("Genre: " + this.getGenre());
        System.out.println("Number of Pages: " + this.getNumberOfPages());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorSurname() {
        return authorSurname;
    }
    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
    public String getAuthorPatronymic() {
        return authorPatronymic;
    }
    public void setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getNumberOfPages() {
        return numberOfPages;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
