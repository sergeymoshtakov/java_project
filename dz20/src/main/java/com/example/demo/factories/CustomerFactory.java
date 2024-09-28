package com.example.demo.factories;

import com.example.demo.model.Customer;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Calendar;

@Service
public class CustomerFactory {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Johnson", "Williams", "Brown"};
    private static final String[] PHONE_NUMBERS = {"123-456-7890", "987-654-3210", "555-555-5555"};
    private static final String[] EMAILS = {"test@example.com", "hello@example.com", "user@example.com"};
    private static final double[] DISCOUNTS = {0.0, 5.0, 10.0, 15.0, 20.0};

    public static Customer createRandomCustomer() {
        String firstName = RandomElements.getRandomElement(FIRST_NAMES);
        String lastName = RandomElements.getRandomElement(LAST_NAMES);
        Date birthDate = getRandomBirthDate();
        java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());
        String phoneNumber = RandomElements.getRandomElement(PHONE_NUMBERS);
        String email = RandomElements.getRandomElement(EMAILS);
        double discount = RandomElements.getRandomElement(DISCOUNTS);

        return new Customer(0, firstName, lastName, sqlDate, phoneNumber, email, discount);
    }

    private static Date getRandomBirthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, RandomElements.getRandomElement(new int[]{1980, 1990, 2000, 2010}));
        calendar.set(Calendar.MONTH, (int) (Math.random() * 12));
        calendar.set(Calendar.DAY_OF_MONTH, (int) (Math.random() * 28) + 1);
        return calendar.getTime();
    }
}