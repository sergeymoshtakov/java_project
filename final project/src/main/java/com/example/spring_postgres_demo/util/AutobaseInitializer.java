package com.example.spring_postgres_demo.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AutobaseInitializer {
    @Autowired
    private final AutobaseDBInitializer autobaseDBInitializer;
    public AutobaseInitializer(AutobaseDBInitializer autobaseDBInitializer) {
        this.autobaseDBInitializer = autobaseDBInitializer;
    }
    public void init() {
        autobaseDBInitializer.cleanAll();
        autobaseDBInitializer.initializeStatuses();
        autobaseDBInitializer.initializeDestinations(5);
        autobaseDBInitializer.initializeCargoTypes(5);
        autobaseDBInitializer.initializeCars(10);
        autobaseDBInitializer.initializeDrivers(10);
        autobaseDBInitializer.initializeRoles();
        autobaseDBInitializer.initializeUsers();
    }
}
