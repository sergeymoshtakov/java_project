package com.example.spring_postgres_demo.service.userservice;

import com.example.spring_postgres_demo.model.User;

import java.util.List;

public interface IUserService {
    void save(User user) ;

    int[] saveUsersList(List<User> users) ;

    void update(User user) ;

    void delete(User user) ;

    List<User> findAll() ;

    void deleteAll() ;

    User findById(long id);

    User findByUsername(String username);
}
