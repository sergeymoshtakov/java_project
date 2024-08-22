package com.company.models;

import com.company.factories.NewspaperFactory;

import java.time.LocalDate;

public class Newspaper extends Publication{
    private LocalDate dateOfPublication;
    private String[] listOfTitles;

    public Newspaper(String name, LocalDate dateOfPublication, String[] listOfTitles) {
        super(name);
        this.dateOfPublication = dateOfPublication;
        this.listOfTitles = new String[listOfTitles.length];
        for (int i = 0; i < listOfTitles.length; i++) {
            this.listOfTitles[i] = listOfTitles[i];
        }
    }

    public Newspaper(Newspaper newspaper) {
        super(newspaper.getName());
        this.dateOfPublication = newspaper.dateOfPublication;
        this.listOfTitles = new String[newspaper.listOfTitles.length];
        for (int i = 0; i < listOfTitles.length; i++) {
            this.listOfTitles[i] = newspaper.listOfTitles[i];
        }
    }

    public Newspaper() {
        this(NewspaperFactory.getRandomNewspaper());
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Date: " + dateOfPublication);
        System.out.println("Titles: ");
        for (int i = 0; i < listOfTitles.length; i++) {
            System.out.println("Title: " + listOfTitles[i]);
        }
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }
    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }
    public void setListOfTitles(String[] listOfTitles) {
        this.listOfTitles = new String[listOfTitles.length];
        for (int i = 0; i < listOfTitles.length; i++) {
            this.listOfTitles[i] = listOfTitles[i];
        }
    }
    public String[] getListOfTitles() {
        String[] result = new String[listOfTitles.length];
        for (int i = 0; i < listOfTitles.length; i++) {
            result[i] = listOfTitles[i];
        }
        return result;
    }
}
