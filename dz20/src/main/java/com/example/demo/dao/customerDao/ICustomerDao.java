package com.example.demo.dao.customerDao;

import com.example.demo.model.Customer;

import java.util.List;

public interface ICustomerDao {
    void save(Customer customer);
    int[] saveCustomersList(List<Customer> customers);
    void update(Customer customer);
    void delete(int customerId);
    List<Customer> findAll();
    Customer findById(int customerId);
    void deleteAll();
}
