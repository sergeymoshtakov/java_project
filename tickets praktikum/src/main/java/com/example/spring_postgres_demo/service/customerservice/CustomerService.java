package com.example.spring_postgres_demo.service.customerservice;

import com.example.spring_postgres_demo.dao.customer.CustomerRepository;
import com.example.spring_postgres_demo.model.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Сохранение нового или обновленного объекта Customer
    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    // Сохранение списка объектов Customer
    @Override
    public long[] saveCustomersList(List<Customer> customers) {
        customerRepository.saveAll(customers);
        // Возвращаем массив идентификаторов сохраненных покупателей
        return customers.stream().mapToLong(Customer::getId).toArray();
    }

    // Обновление существующего объекта Customer
    @Override
    public void update(Customer customer) {
        if (customer.getId() != 0) {
            Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
            if (existingCustomer.isPresent()) {
                customerRepository.save(customer);
            }
        }
    }

    // Удаление объекта Customer
    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    // Поиск всех объектов Customer
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // Удаление всех объектов Customer
    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    // Поиск объекта Customer по идентификатору
    @Override
    public Customer findById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Поиск идентификатора объекта Customer по имени
    @Override
    public long findIdByName(String name) {
        List<Long> ids = customerRepository.findIdByName(name);
        if (!ids.isEmpty()) {
            return ids.get(0); // Возвращаем первый найденный ID
        }
        throw new EntityNotFoundException("No Customer found with name: " + name);
    }
}
