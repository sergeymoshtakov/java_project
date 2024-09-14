package org.example;

import org.example.enums.CurrencyCourse;
import org.example.models.converter.CurrencyConverter;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        double amount;
        int fromChoice;
        int toChoice;

        System.out.println("Enter the amount to convert: ");
        amount = scanner.nextDouble();

        do {
            System.out.println("Choose your source currency: ");
            for (CurrencyCourse currencyCourse : CurrencyCourse.values()) {
                System.out.println(currencyCourse + " - " + currencyCourse.ordinal());
            }
            fromChoice = scanner.nextInt();
        } while (fromChoice < 0 || fromChoice >= CurrencyCourse.values().length);

        do {
            System.out.println("Choose your target currency: ");
            for (CurrencyCourse currencyCourse : CurrencyCourse.values()) {
                System.out.println(currencyCourse + " - " + currencyCourse.ordinal());
            }
            toChoice = scanner.nextInt();
        } while (toChoice < 0 || toChoice >= CurrencyCourse.values().length);

        CurrencyCourse fromCurrency = CurrencyCourse.values()[fromChoice];
        CurrencyCourse toCurrency = CurrencyCourse.values()[toChoice];

        double result = converter.convert(fromCurrency, toCurrency, amount);

        System.out.println(amount + " " + fromCurrency + " = " + result + " " + toCurrency);
    }
}
