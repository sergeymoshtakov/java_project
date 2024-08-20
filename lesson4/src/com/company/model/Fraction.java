package com.company.model;

import com.company.factories.FractionFactory;

import java.util.Scanner;

public class Fraction {
    private int numerator; // числитель
    private int denominator; // знаменатель

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction(Fraction fraction) {
        this(fraction.numerator, fraction.denominator);
    }

    public Fraction() {
        this(FractionFactory.getRandomFraction());
    }

    public void inputFraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter numerator: ");
        this.setNumerator(scanner.nextInt());
        int denominator;
        do{
            System.out.print("Enter denominator (must not be zero): ");
            denominator = scanner.nextInt();
        }while(denominator == 0);
        this.setDenominator(denominator);
    }

    public void printFraction() {
        System.out.print(numerator + "/" + denominator);
    }

    public Fraction add(Fraction fraction) {
        Fraction result = new Fraction();
        int numerator = this.numerator * fraction.getDenominator() + fraction.getNumerator() * this.denominator;
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction add(int number) {
        Fraction result = new Fraction();
        Fraction fraction = new Fraction(number);
        int numerator = this.numerator * fraction.getDenominator() + fraction.getNumerator() * this.denominator;
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction subtract(Fraction fraction) {
        Fraction result = new Fraction();
        int numerator = this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator;
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction subtract(int number) {
        Fraction result = new Fraction();
        Fraction fraction = new Fraction(number);
        int numerator = this.numerator * fraction.getDenominator() - fraction.getNumerator() * this.denominator;
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction multiply(Fraction fraction) {
        Fraction result = new Fraction();
        int numerator = this.numerator * fraction.getNumerator();
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction multiply(int number) {
        Fraction result = new Fraction();
        Fraction fraction = new Fraction(number);
        int numerator = this.numerator * fraction.getNumerator();
        int denominator = this.denominator * fraction.getDenominator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction divide(Fraction fraction) {
        Fraction result = new Fraction();
        int numerator = this.numerator * fraction.getDenominator();
        int denominator = this.denominator * fraction.getNumerator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public Fraction divide(int number) {
        Fraction result = new Fraction();
        Fraction fraction = new Fraction(number);
        int numerator = this.numerator * fraction.getDenominator();
        int denominator = this.denominator * fraction.getNumerator();
        result.setNumerator(numerator);
        result.setDenominator(denominator);
        return result;
    }

    public int getNumerator() {
        return numerator;
    }
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }
    public int getDenominator() {
        return denominator;
    }
    public void setDenominator(int denominator) {
        if(denominator == 0){
            System.out.println("Denominator must not be zero");
        } else {
            this.denominator = denominator;
        }
    }
}
