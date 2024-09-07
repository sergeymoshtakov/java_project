package com.company.menu;

import java.util.Scanner;

public class MenuPublisher {
    private final MenuExecutor executor;
    private final Scanner scanner;

    public MenuPublisher(MenuExecutor executor) {
        this.scanner = new Scanner(System.in);
        this.executor = executor;
    }

    public void showMenu(){
        int choice = 0;
        while(choice != 8){
            System.out.println("\n1. Add employee");
            System.out.println("2. Edit employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Search employee by surname");
            System.out.println("5. Print all employees by age");
            System.out.println("6. Print all employees by surname initial");
            System.out.println("7. Save all employees to file");
            System.out.println("8. Exit");
            do{
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
            }while(choice <1 || choice > 8);

            switch(choice){
                case 1:
                    executor.addEmployee(scanner);
                    break;
                case 2:
                    executor.editEmployee(scanner);
                    break;
                case 3:
                    executor.deleteEmployee(scanner);
                    break;
                case 4:
                    executor.searchEmployee(scanner);
                    break;
                case 5:
                    executor.printEmployeesByAge(scanner);
                    break;
                case 6:
                    executor.printEmployeesByFirstLetter(scanner);
                    break;
                case 7:
                    executor.saveAllEmployeesToFile();
                    break;
                case 8:
                    executor.saveAllEmployeesToFile();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
