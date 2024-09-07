package com.company.factories;

import com.company.models.Employee;
import com.company.util.RandomElements;

public class EmployeeFactory {
    public static final String[] names = {"John", "James", "Bill", "Mathew", "Adam", "Leo"};
    public static final String[] surnames = {"Stone", "Smith", "Jackson", "Miller", "Adams"};
    public static final int[] ages = {20, 30, 40, 50, 60};
    public static Employee createEmployee() {
        return new Employee(
                RandomElements.getRandomElement(names),
                RandomElements.getRandomElement(surnames),
                RandomElements.getRandomElement(ages)
        );
    }
}
