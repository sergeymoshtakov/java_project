package com.company.menus;

import com.company.models.Street;

import java.util.Scanner;

public class StreetMenuPublisher {
    private static final Scanner scanner = new Scanner(System.in);
    private boolean streetCreated;
    private final StreetMenuExecutor streetMenuExecutor;

    public StreetMenuPublisher() {
        streetCreated = false;
        streetMenuExecutor = new StreetMenuExecutor();
    }
    public void showMenu() {
        int choice = 0;
        while (choice != 6){
            System.out.println("\n1 - Create street");
            System.out.println("2 - Show info");
            System.out.println("3 - Add house");
            System.out.println("4 - Remove house");
            System.out.println("5 - Find shop department");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    streetMenuExecutor.initializeStreet(scanner);
                    streetCreated = true;
                    break;
                case 2:
                    if (streetCreated) {
                        streetMenuExecutor.printInfo();
                    } else {
                        System.out.println("Please initialize street first");
                    }
                    break;
                case 3:
                    if (streetCreated) {
                        int houseType = showHouseCreationMenu();
                        streetMenuExecutor.addHouse(scanner, houseType);
                    } else {
                        System.out.println("Please initialize street first");
                    }
                    break;
                case 4:
                    if (streetCreated) {
                        streetMenuExecutor.removeHouse(scanner);
                    } else {
                        System.out.println("Please initialize street first");
                    }
                    break;
                case 5:
                    if (streetCreated) {
                        streetMenuExecutor.findShopDepartment(scanner);
                    } else {
                        System.out.println("Please initialize street first");
                    }
                    break;
                case 6:
                    System.out.println("Work with street finished! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again!");
            }
        }
    }

    public int showHouseCreationMenu(){
        int choice;
        do {
            System.out.println("\nChoose house type: ");
            System.out.println("1 - Hospital");
            System.out.println("2 - Living House");
            System.out.println("3 - School");
            System.out.println("4 - Shop");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 4);
        return choice;
    }
}
