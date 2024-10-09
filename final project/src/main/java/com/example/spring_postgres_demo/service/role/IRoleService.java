package com.example.spring_postgres_demo.service.role;

import com.example.spring_postgres_demo.model.Role;

import java.util.List;

public interface IRoleService {
    void save(Role role) ;

    int[] saveRolesList(List<Role> roles) ;

    void update(Role role) ;

    void delete(Role role) ;

    List<Role> findAll() ;

    void deleteAll() ;

    Role findById(int id);

    Role findByName(String admin);
}
