package com.company.model;


import com.company.factories.PersonFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String city;
    private String country;
    private String address;

    public Person(String name, String surname, String patronymic, LocalDate dateOfBirth, String phoneNumber, String city, String country, String address) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
        this.address = address;
    }

    public Person(Person person) {
        this(person.getName(), person.getSurname(), person.getPatronymic(), person.getDateOfBirth(), person.getPhoneNumber(), person.getCity(), person.getCountry(), person.getAddress());
    }

    public Person() {
        this(PersonFactory.getRandomPerson());
    }

    public Person(String name, String surname) {
        this(PersonFactory.getRandomPerson());
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, String patronymic) {
        this(PersonFactory.getRandomPerson());
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public void inputPerson(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        setName(scanner.nextLine());
        System.out.println("Enter your surname: ");
        setSurname(scanner.nextLine());
        System.out.println("Enter your patronymic: ");
        setPatronymic(scanner.nextLine());
        int day, month, year;
        do{
            System.out.println("Enter day of birth: ");
            day = scanner.nextInt();
            System.out.println("Enter month of birth: ");
            month = scanner.nextInt();
            System.out.println("Enter year of birth: ");
            year = scanner.nextInt();
        }while(day < 0 || month < 0 || month > 12 || year < 0);
        setDateOfBirth(LocalDate.of(year, month, day));
        System.out.println("Enter phone number: ");
        setPhoneNumber(scanner.nextLine());
        System.out.println("Enter city: ");
        setCity(scanner.nextLine());
        System.out.println("Enter country: ");
        setCountry(scanner.nextLine());
        System.out.println("Enter address: ");
        setAddress(scanner.nextLine());
        scanner.close();
    }

    public void printPerson(){
        System.out.println("Person:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Patronymic: " + patronymic);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
        System.out.println("Address: " + address);
    }

    public void printPerson(boolean shortFormat) {
        if (shortFormat) {
            System.out.println("Person: " + name + " " + surname);
        } else {
            printPerson();
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public void setName(String name, boolean toUpperCase) {
        if(toUpperCase){
            this.name = name.toUpperCase();
        } else {
            this.name = name;
        }
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setSurname(String surname, boolean toUpperCase) {
        if(toUpperCase){
            this.surname = surname.toUpperCase();
        } else {
            this.surname = surname;
        }
    }
    public String getSurname(){
        return surname;
    }
    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }
    public String getPatronymic(){
        return patronymic;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }
    public void setDateOfBirth(int year, int month, int day) {
        this.dateOfBirth = LocalDate.of(year, month, day);
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return country;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
}
