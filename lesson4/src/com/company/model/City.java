package com.company.model;

import com.company.factories.CityFactory;

import java.util.Scanner;

public class City {
    private String name;
    private String region;
    private String country;
    private int population;
    private int postIndex;
    private String telephoneCode;

    public City(String name, String region, String country, int population, int postIndex, String telephoneCode) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.population = population;
        this.postIndex = postIndex;
        this.telephoneCode = telephoneCode;
    }

    public City(City city) {
        this(city.getName(), city.getRegion(), city.getCountry(), city.getPopulation(), city.getPostIndex(), city.getTelephoneCode());
    }

    public City(){
        this(CityFactory.getRandomCity());
    }

    public City(String name, String country){
        this(CityFactory.getRandomCity());
        this.name = name;
        this.country = country;
    }

    public City(String name, String region, String country){
        this(CityFactory.getRandomCity());
        this.name = name;
        this.region = region;
        this.country = country;
    }

    public void inputCity(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the city: ");
        setName(scanner.nextLine());
        System.out.println("Enter the region of the city: ");
        setRegion(scanner.nextLine());
        System.out.println("Enter the country of the city: ");
        setCountry(scanner.nextLine());
        System.out.println("Enter the population of the city: ");
        setPopulation(scanner.nextInt());
        System.out.println("Enter the post index of the city: ");
        setPostIndex(scanner.nextInt());
        System.out.println("Enter the telephone code of the city: ");
        setTelephoneCode(scanner.nextLine());
    }
    public void printCity(){
        System.out.println("City:");
        System.out.println("Name: " + getName());
        System.out.println("Region: " + getRegion());
        System.out.println("Country: " + getCountry());
        System.out.println("Population: " + getPopulation());
        System.out.println("Post Index: " + getPostIndex());
        System.out.println("Telephone Code: " + getTelephoneCode());
    }

    public void printCity(boolean shortFormat){
        if(shortFormat){
            System.out.println("City: " + getName() + ", " + getCountry());
        } else {
            printCity();
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public void setPostIndex(int postIndex) {
        if(postIndex > 999 && postIndex < 10000){
            this.postIndex = postIndex;
        }
    }
    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }
    public String getName() {
        return name;
    }
    public String getRegion() {
        return region;
    }
    public String getCountry() {
        return country;
    }
    public int getPopulation() {
        return population;
    }
    public int getPostIndex() {
        return postIndex;
    }
    public String getTelephoneCode() {
        return telephoneCode;
    }
}
