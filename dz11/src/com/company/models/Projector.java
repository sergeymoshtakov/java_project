package com.company.models;

public class Projector {
    private String name;
    private int yearOfProduction;
    private double price;
    private String producer;

    public Projector(String name, int yearOfProduction, double price, String producer) {
        this.name = name;
        this.yearOfProduction = yearOfProduction;
        this.price = price;
        this.producer = producer;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.yearOfProduction + ", " + this.price + ", " + this.producer;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYearOfProduction() {
        return yearOfProduction;
    }
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
    }
}
