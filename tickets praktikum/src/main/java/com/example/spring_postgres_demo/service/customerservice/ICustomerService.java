package com.example.spring_postgres_demo.service.customerservice;

import com.example.spring_postgres_demo.model.Customer;

import java.util.List;

public interface ICustomerService {

    void save(Customer status) ;

    long[] saveCustomersList(List<Customer> statuses) ;

    void update(Customer status) ;

    void delete(Customer status) ;

    List<Customer> findAll() ;

    void deleteAll() ;

    Customer findById(long id);

    long findIdByName(String name);
}
