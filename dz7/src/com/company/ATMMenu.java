package com.company;

import com.company.models.Bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMMenu {
    private Bank bank;
    private static final Scanner scanner = new Scanner(System.in);

    public ATMMenu(Bank bank) {
        this.bank = bank;
    }

    public void displayMenu() {
        int choice = -1;
        do {
            System.out.println("\nWelcome to the ATM System");
            System.out.println("1. Check total money in all ATMs");
            System.out.println("2. Deposit money to an ATM");
            System.out.println("3. Withdraw money from an ATM");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        checkTotalMoneyInATMs();
                        break;
                    case 2:
                        depositMoneyToATM();
                        break;
                    case 3:
                        withdrawMoneyFromATM();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        } while (choice != 4);
    }

    private void checkTotalMoneyInATMs() {
        try {
            int totalMoney = bank.getTotalMoneyInATMs();
            System.out.println("Total money available in all ATMs: " + totalMoney + " UAH");
        } catch (Exception e) {
            System.out.println("Error checking total money: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void depositMoneyToATM() {
        try {
            System.out.print("Enter the ATM index (0 to " + (bank.getQuantity() - 1) + "): ");
            int atmIndex = scanner.nextInt();
            if (atmIndex >= 0 && atmIndex < bank.getQuantity()) {
                bank.getAtms()[atmIndex].depositMoney(scanner);
            } else {
                System.out.println("Invalid ATM index. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error during deposit: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void withdrawMoneyFromATM() {
        try {
            System.out.print("Enter the ATM index (0 to " + (bank.getQuantity() - 1) + "): ");
            int atmIndex = scanner.nextInt();
            if (atmIndex >= 0 && atmIndex < bank.getQuantity()) {
                bank.getAtms()[atmIndex].withdrawMoney(scanner);
            } else {
                System.out.println("Invalid ATM index. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
