package org.example;

import org.example.enums.LengthUnit;
import org.example.models.length.LengthConverter;

import java.util.Scanner;

public class TaskFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LengthConverter converter = new LengthConverter();

        System.out.println("Enter the value to convert: ");
        double value = scanner.nextDouble();

        LengthUnit fromUnit;
        LengthUnit toUnit;
        int fromChoice, toChoice;

        do {
            System.out.println("Choose the source unit:");
            for (LengthUnit unit : LengthUnit.values()) {
                System.out.println(unit.name() + " - " + unit.ordinal());
            }
            fromChoice = scanner.nextInt();
        } while (fromChoice < 0 || fromChoice >= LengthUnit.values().length);
        fromUnit = LengthUnit.values()[fromChoice];

        do {
            System.out.println("Choose the target unit:");
            for (LengthUnit unit : LengthUnit.values()) {
                System.out.println(unit.name() + " - " + unit.ordinal());
            }
            toChoice = scanner.nextInt();

        } while (toChoice < 0 || toChoice > LengthUnit.values().length);
        toUnit = LengthUnit.values()[toChoice];

        double result = converter.convert(value, fromUnit, toUnit);
        System.out.println("Converted value: " + result + " " + toUnit.name().toLowerCase());
    }
}
