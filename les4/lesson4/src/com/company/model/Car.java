package com.company.model;

import com.company.factories.CarFactory;

import java.util.Scanner;

public class Car {
    private String name;
    private String producer;
    private int year;
    private double volume;

    public Car(String name, String producer, int year, double volume) {
        this.name = name;
        this.producer = producer;
        this.year = year;
        this.volume = volume;
    }

    public Car(Car car) {
        this(car.getName(), car.getProducer(), car.getYear(), car.getVolume());
    }

    public Car(){
        this(CarFactory.getRandomCar());
    }

    public Car(String name){
        this(CarFactory.getRandomCar());
        this.name = name;
    }

    public void inputCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the car: ");
        name = scanner.nextLine();
        System.out.print("Enter the producer of the car: ");
        producer = scanner.nextLine();
        System.out.print("Enter the year of production of the car: ");
        year = scanner.nextInt();
        System.out.print("Enter the volume of engine of the car: ");
        volume = scanner.nextDouble();
    }

    public void printCar(){
        System.out.println("Name: " + name);
        System.out.println("Producer: " + producer);
        System.out.println("Year of production: " + year);
        System.out.println("Volume of engine: " + volume);
    }

    public void printCar(boolean shortFormat){
        if(shortFormat){
            System.out.println("Car name: " + name);
        } else {
            printCar();
        }
    }

    public String getName(){
        return name;
    }
    public String getProducer(){
        return producer;
    }
    public int getYear(){
        return year;
    }
    public double getVolume(){
        return volume;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setProducer(String producer){
        this.producer = producer;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setVolume(double volume){
        this.volume = volume;
    }
}
