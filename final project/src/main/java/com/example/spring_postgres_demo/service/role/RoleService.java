package com.example.spring_postgres_demo.service.role;

import com.example.spring_postgres_demo.dao.role.RoleRepository;
import com.example.spring_postgres_demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public int[] saveRolesList(List<Role> roles) {
        List<Role> savedRoles = roleRepository.saveAll(roles);
        return savedRoles.stream().mapToInt(Role::getId).toArray();
    }

    @Override
    public void update(Role role) {
        if (roleRepository.existsById(role.getId())) {
            roleRepository.save(role);
        }
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    @Override
    public Role findByName(String admin) {
        for (Role role : roleRepository.findAll()) {
            if (role.getName().equals(admin)) {
                return role;
            }
        }
        return null;
    }
}
