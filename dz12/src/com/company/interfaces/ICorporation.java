package com.company.interfaces;

import com.company.models.Employee;

import java.util.Scanner;

public interface ICorporation {
    public void initializeCorporation();
    public void enterData(Scanner scanner);
    public void editEmployee(Scanner scanner);
    public void deleteEmployee(Scanner scanner);
    public Employee searchEmployee(String surname);
    public void printAllEmployees(int age);
    public void printAllEmployees(char firstLetter);
    public void saveAllEmployees();
    public void saveFoundInfo(Employee employee);
    public void initializeCorporationSerializable();
    public void saveAllEmployeesSerializable();
}
