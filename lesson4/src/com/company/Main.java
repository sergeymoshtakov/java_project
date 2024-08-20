package com.company;

import com.company.factories.CityFactory;
import com.company.factories.CountryFactory;
import com.company.factories.FractionFactory;
import com.company.factories.PersonFactory;
import com.company.model.*;

public class Main {
    public static void main(String[] args) {
        Person person1 = PersonFactory.getRandomPerson();
        person1.printPerson(true);
        City city1 = CityFactory.getRandomCity();
        city1.printCity(true);
        Country country1 = CountryFactory.getRandomCountry();
        country1.printCountry(true);
        Fraction fraction1 = FractionFactory.getRandomFraction();
        fraction1.printFraction();
        System.out.print(" + ");
        int fraction2 = 1;
        System.out.print(fraction2);
        System.out.print(" = ");
        Fraction fraction3 = fraction1.add(fraction2);
        fraction3.printFraction();
        System.out.println();
        Book book = new Book();
        book.printBook(true);
        Car car = new Car();
        car.printCar(true);
    }
}