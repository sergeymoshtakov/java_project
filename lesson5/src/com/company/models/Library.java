package com.company.models;

import com.company.factories.*;

public class Library implements ILibrary, IPrintable{
    public static final int DEFAULT_CAPACITY = 100;

    private Publication[] publications;
    private int count;

    public Library(int capacity) {
        publications = new Publication[capacity];
        count = 0;
    }

    public Library(Publication[] publications) {
        this.publications = new Publication[publications.length];
        for (int i = 0; i < publications.length; i++) {
            this.publications[i] = publications[i];
        }
        this.count = publications.length;
    }

    public Library() {
        publications = new Publication[DEFAULT_CAPACITY];
        count = 0;
    }

    public void initializeLibrary(){
        addPublication(BookFactory.getRandomBook());
        addPublication(AlmanacFactory.getRandomAlmanac());
        addPublication(NewspaperFactory.getRandomNewspaper());
    }

    public void addPublication(Publication publication){
        if (count < publications.length) {
            publications[count++] = publication;
        } else {
            System.out.println("Library is full. Cannot add more publications.");
        }
    }

    public void addPublication(Book book){
        if (count < publications.length) {
            publications[count++] = new Book(book);
        } else {
            System.out.println("Library is full. Cannot add more publications.");
        }
    }

    public void addPublication(Almanac almanac){
        if (count < publications.length) {
            publications[count++] = new Almanac(almanac);
        } else {
            System.out.println("Library is full. Cannot add more publications.");
        }
    }

    public void addPublication(Newspaper newspaper){
        if (count < publications.length) {
            publications[count++] = new Newspaper(newspaper);
        } else {
            System.out.println("Library is full. Cannot add more publications.");
        }
    }

    public boolean removePublicationByName(String name) {
        for (int i = 0; i < count; i++) {
            if (publications[i].getName().equalsIgnoreCase(name)) {
                publications[i] = publications[count - 1];
                publications[count - 1] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void printInfo() {
        if (count == 0) {
            System.out.println("The library catalog is empty.");
            return;
        }
        System.out.println("Library: ");
        for (int i = 0; i < count; i++) {
            publications[i].printInfo();
        }
    }

    public Publication searchByName(String name) {
        for (int i = 0; i < count; i++) {
            if (publications[i] instanceof Almanac) {
                Almanac almanac = (Almanac) publications[i];
                for (int j = 0; j < almanac.getBooks().length; j++) {
                    if (almanac.getBooks()[j].getName().equalsIgnoreCase(name)) {
                        return almanac.getBooks()[j];
                    }
                }
            } else {
                if (publications[i].getName().equalsIgnoreCase(name)) {
                    return publications[i];
                }
            }
        }
        return null;
    }

    public Book searchByAuthor(String author) {
        for (int i = 0; i < count; i++) {
            if (publications[i] instanceof Almanac) {
                Almanac almanac = (Almanac) publications[i];
                for (Book book : almanac.getBooks()) {
                    if (book.getAuthor().equalsIgnoreCase(author)) {
                        return book;
                    }
                }
            } else if (publications[i] instanceof Book) {
                Book book = (Book) publications[i];
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    return book;
                }
            }
        }
        return null;
    }
}
