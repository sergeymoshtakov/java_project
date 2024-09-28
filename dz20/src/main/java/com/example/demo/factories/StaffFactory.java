package com.example.demo.factories;

import com.example.demo.model.Position;
import com.example.demo.model.Staff;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class StaffFactory {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie"};
    private static final String[] LAST_NAMES = {"Doe", "Smith", "Johnson", "Williams", "Brown"};
    private static final String[] PHONE_NUMBERS = {"123-456-7890", "987-654-3210", "555-555-5555"};
    private static final String[] EMAILS = {"test@example.com", "hello@example.com", "user@example.com"};

    public static Staff createRandomStaff() {
        String firstName = RandomElements.getRandomElement(FIRST_NAMES);
        String lastName = RandomElements.getRandomElement(LAST_NAMES);
        String phoneNumber = RandomElements.getRandomElement(PHONE_NUMBERS);
        String email = RandomElements.getRandomElement(EMAILS);
        Position position = PositionFactory.createRandomPosition();
        return new Staff(0, firstName, lastName, phoneNumber, email, position);
    }

    public static Staff createRandomStaff(String positionName) {
        String firstName = RandomElements.getRandomElement(FIRST_NAMES);
        String lastName = RandomElements.getRandomElement(LAST_NAMES);
        String phoneNumber = RandomElements.getRandomElement(PHONE_NUMBERS);
        String email = RandomElements.getRandomElement(EMAILS);
        Position position = new Position(0, positionName);
        return new Staff(0, firstName, lastName, phoneNumber, email, position);
    }
}
