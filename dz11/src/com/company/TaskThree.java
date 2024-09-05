package com.company;

import com.company.enums.Color;
import com.company.enums.DeviceType;
import com.company.factories.DeviceFactory;
import com.company.models.Device;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskThree {
    public static void main(String[] args) {
        List<Device> devices = Stream.generate(DeviceFactory::getDevice).limit(10).toList();
        devices.forEach(System.out::println);
        try(Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                for (Color color : Color.values()) {
                    System.out.println(color.toString() + " - " + color.ordinal());
                }
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } while(choice < 0 || choice >= Color.values().length);
            Color color = Color.values()[choice];
            devices.stream().filter(device -> device.getColor() == color).forEach(System.out::println);

            int year;
            do {
                System.out.print("Enter year: ");
                year = scanner.nextInt();
            } while(year < 0);
            int finalYear = year;
            devices.stream().filter(device -> device.getYearOfProduction() == finalYear).forEach(System.out::println);

            int price;
            do {
                System.out.println("Enter price: ");
                price = scanner.nextInt();
            } while(price < 0);
            int finalPrice = price;
            devices.stream().filter(device -> device.getPrice() > finalPrice).forEach(System.out::println);

            choice = -1;
            do {
                for (DeviceType type : DeviceType.values()) {
                    System.out.println(type.toString() + " - " + type.ordinal());
                }
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } while(choice < 0 || choice >= DeviceType.values().length);
            DeviceType type = DeviceType.values()[choice];
            devices.stream().filter(device -> device.getType() == type).forEach(System.out::println);

            int startYear;
            int endYear;
            do {
                System.out.print("Enter starting year: ");
                startYear = scanner.nextInt();
                System.out.println("Enter ending year: ");
                endYear = scanner.nextInt();
            } while(startYear < 0 && startYear < endYear);
            int finalEndYear = endYear;
            int finalStartYear = startYear;
            devices.stream().filter(device -> device.getYearOfProduction() <= finalEndYear && device.getYearOfProduction() >= finalStartYear).forEach(System.out::println);
        }
    }
}
