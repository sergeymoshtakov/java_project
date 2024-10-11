package com.example.spring_postgres_demo;

import com.example.spring_postgres_demo.menu.MainMenu;
import com.example.spring_postgres_demo.util.TicketServiceInitializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class AppStarter {
    @Autowired
    private TicketServiceInitializer ticketServiceInitializer;

    @Autowired
    private MainMenu mainMenu;

    @Bean
    public ApplicationRunner init() {
        log.info("ApplicationRunner has started");
        return args -> {
            ticketServiceInitializer.init();
            mainMenu.start();
        };
    }
}
