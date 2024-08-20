package com.company.model;

import com.company.factories.CountryFactory;

import java.util.Scanner;

public class Country {
    private String name;
    private String continent;
    private int population;
    private String telephoneCode;
    private String capital;
    private String[] cities;

    public Country(String name, String continent, int population, String telephoneCode, String capital, String[] cities) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.telephoneCode = telephoneCode;
        this.capital = capital;
        this.cities = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            this.cities[i] = cities[i];
        }
    }

    public Country(Country country) {
        this(country.getName(), country.getContinent(), country.getPopulation(), country.getTelephoneCode(), country.getCapital(), country.getCities());
    }

    public Country() {
        this(CountryFactory.getRandomCountry());
    }

    public void printCountry() {
        System.out.println("Name: " + name);
        System.out.println("Continent: " + continent);
        System.out.println("Population: " + population);
        System.out.println("TelephoneCode: " + telephoneCode);
        System.out.println("Capital: " + capital);
        System.out.println("Cities: ");
        for(int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }
    }

    public void printCountry(boolean shortFormat){
        if(shortFormat){
            System.out.println("Country: " + name);
        } else {
            printCountry();
        }
    }

    public void inputCountry(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the country: ");
        this.setName(sc.nextLine());
        System.out.println("Enter the continent of the country: ");
        this.setContinent(sc.nextLine());
        System.out.println("Enter the population of the country: ");
        this.setPopulation(sc.nextInt());
        System.out.println("Enter the telephoneCode of the country: ");
        this.setTelephoneCode(sc.nextLine());
        System.out.println("Enter the capital of the country: ");
        this.setCapital(sc.nextLine());
        System.out.println("Enter a number of the cities of the country: ");
        int quantityOfCities = Integer.parseInt(sc.nextLine());
        String[] cities = new String[quantityOfCities];
        int i = 0;
        String city;
        while(i < quantityOfCities){
            System.out.println("Enter the city: ");
            city = sc.nextLine();
            cities[i] = city;
            i++;
        }
        this.setCities(cities);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public String getContinent() {
        return continent;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public int getPopulation() {
        return population;
    }
    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }
    public String getTelephoneCode() {
        return telephoneCode;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getCapital() {
        return capital;
    }
    public void setCities(String[] cities) {
        this.cities = new String[cities.length];
        for(int i=0; i < cities.length; i++) {
            this.cities[i] = cities[i];
        }
    }
    public String[] getCities() {
        return cities;
    }
}
