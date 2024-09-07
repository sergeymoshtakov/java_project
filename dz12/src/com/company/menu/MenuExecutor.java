package com.company.menu;

import com.company.interfaces.ICorporation;
import com.company.models.Corporation;
import com.company.models.Employee;

import java.util.Scanner;

public class MenuExecutor {
    private final ICorporation corporation;
    public MenuExecutor() {
        this.corporation = new Corporation();
        this.corporation.initializeCorporation();
    }

    public void addEmployee(Scanner scanner) {
        this.corporation.enterData(scanner);
    }

    public void editEmployee(Scanner scanner) {
        this.corporation.editEmployee(scanner);
    }

    public void deleteEmployee(Scanner scanner) {
        this.corporation.deleteEmployee(scanner);
    }

    public void searchEmployee(Scanner scanner) {
        System.out.print("Enter the surname of the employee: ");
        String surname = scanner.nextLine();
        Employee employee = this.corporation.searchEmployee(surname);
        if (employee != null) {
            System.out.println(employee.toString());

            int choice;
            do {
                System.out.println("1. Save founded employee");
                System.out.println("2. Don't save founded employee");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            } while(choice < 0 || choice > 2);
            if (choice == 1){
                this.corporation.saveFoundInfo(employee);
            }
        } else {
            System.out.println("Employee not found");
        }
    }

    public void printEmployeesByAge(Scanner scanner) {
        int age;
        do {
            System.out.print("Enter the age of the employee: ");
            age = scanner.nextInt();
        } while(age < 0);
        this.corporation.printAllEmployees(age);
    }

    public void printEmployeesByFirstLetter(Scanner scanner) {
        char c;
        System.out.println("Enter the first letter of the employee: ");
        c = scanner.next().charAt(0);
        this.corporation.printAllEmployees(c);
    }

    public void saveAllEmployeesToFile() {
        this.corporation.saveAllEmployees();
    }
}
