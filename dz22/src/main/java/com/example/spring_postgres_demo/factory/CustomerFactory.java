package com.example.spring_postgres_demo.factory;

import com.example.spring_postgres_demo.model.Customer;
import com.example.spring_postgres_demo.util.RandomElements;
import org.springframework.stereotype.Service;

@Service
public class CustomerFactory implements IFactory{
    public static final String[] NAMES = {"John Smith", "Anna Williams", "Sophia Jones", "Peter Parker"};
    public static final String[] EMAILS = {"smith@gmail.com", "williams@gmail.com", "jones@gmail.com"};
    public static final String[] PHONES = {"3801234567", "3801235476", "3809876543"};

    @Override
    public Customer getRandomElement() {
        String name = RandomElements.getRandomElement(NAMES);
        String email = RandomElements.getRandomElement(EMAILS);
        String phone = RandomElements.getRandomElement(PHONES);
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        return customer;
    }
}
