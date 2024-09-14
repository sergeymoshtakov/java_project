package org.example;

import org.example.enums.WeightUnit;
import org.example.models.weight.WeightConverter;

import java.util.Scanner;

public class TaskFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeightConverter converter = new WeightConverter();

        System.out.println("Enter the value to convert: ");
        double value = scanner.nextDouble();

        WeightUnit fromUnit;
        WeightUnit toUnit;
        int fromChoice, toChoice;

        do {
            System.out.println("Choose the source unit:");
            for (WeightUnit unit : WeightUnit.values()) {
                System.out.println(unit.name() + " - " + unit.ordinal());
            }
            fromChoice = scanner.nextInt();
        } while (fromChoice < 0 || fromChoice >= WeightUnit.values().length);

        fromUnit = WeightUnit.values()[fromChoice];

        do {
            System.out.println("Choose the target unit:");
            for (WeightUnit unit : WeightUnit.values()) {
                System.out.println(unit.name() + " - " + unit.ordinal());
            }
            toChoice = scanner.nextInt();
        } while (toChoice < 0 || toChoice > WeightUnit.values().length);

        toUnit = WeightUnit.values()[toChoice];

        double result = converter.convert(value, fromUnit, toUnit);
        System.out.println("Converted value: " + result + " " + toUnit.name().toLowerCase());
    }
}
