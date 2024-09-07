package com.company.models;

import com.company.factories.EmployeeFactory;
import com.company.interfaces.ICorporation;
import com.company.util.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Corporation implements ICorporation {
    private List<Employee> employees;
    private File file;
    private File fileSerializable;
    private File foundInfo;

    public Corporation() {
        this.employees = new ArrayList<>();
        this.file = new File("employees.txt");
        this.foundInfo = new File("foundEmployees.txt");
        this.fileSerializable = new File("fileSerializable.txt");
    }

    private Employee findEmployee(Scanner scanner) {
        int index = 1;
        for (Employee employee : employees) {
            System.out.println(employee.toString() + " - " + index);
            index++;
        }
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
        } while(choice < 1 || choice > employees.size());

        return employees.get(choice - 1);
    }

    public void saveFoundInfo(Employee employee) {
        try (Writer fileWriter = new FileWriter(foundInfo, true)){
            fileWriter.write(employee.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initializeCorporationSerializable() {
        if (FileUtils.isFileEmpty(fileSerializable)) {
            // Test data if file is empty
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            saveAllEmployees();
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSerializable))) {
                employees = (List<Employee>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading employees: " + e.getMessage());
            }
        }
    }

    @Override
    public void saveAllEmployeesSerializable() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSerializable))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            throw new RuntimeException("Error saving employees: " + e.getMessage(), e);
        }
    }

    @Override
    public void initializeCorporation() {
        if(FileUtils.isFileEmpty(file)) {
            // test info if empty
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            employees.add(EmployeeFactory.createEmployee());
            saveAllEmployees();
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
                List<String> strings = bufferedReader.lines().toList();
                for (String string : strings) {
                    String[] elements = string.split(" ");
                    employees.add(new Employee(elements[0], elements[1], Integer.parseInt(elements[2])));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());;
            }
        }
    }

    @Override
    public void enterData(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        employees.add(new Employee(name, surname, age));
    }

    @Override
    public void editEmployee(Scanner scanner) {
        Employee employee = findEmployee(scanner);
        scanner.nextLine();
        System.out.print("Enter employee name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter employee surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        employee = new Employee(firstName, surname, age);
    }

    @Override
    public void deleteEmployee(Scanner scanner) {
        Employee employee = findEmployee(scanner);

        if (employees.remove(employee)) {
            System.out.println("Employee was deleted.");
        }
    }

    @Override
    public Employee searchEmployee(String surname) {
        return employees.stream().filter(x -> x.getSurname().equals(surname)).findFirst().orElse(null);
    }

    @Override
    public void printAllEmployees(int age) {
        employees.stream().filter(employee -> employee.getAge() == age).forEach(employee -> System.out.println(employee.toString()));
    }

    @Override
    public void printAllEmployees(char firstLetter) {
        employees.stream().filter(employee -> employee.getSurname().charAt(0) == firstLetter).forEach(employee -> System.out.println(employee.toString()));
    }

    @Override
    public void saveAllEmployees() {
        try(Writer fileWriter = new FileWriter(file)) {
            for (Employee employee : employees) {
                fileWriter.write(employee.toString()+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
