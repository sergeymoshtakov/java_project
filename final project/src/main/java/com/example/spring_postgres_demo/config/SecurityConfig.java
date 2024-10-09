package com.example.spring_postgres_demo.config;

import com.example.spring_postgres_demo.exceptions.UsernameNotFoundException;
import com.example.spring_postgres_demo.model.User;
import com.example.spring_postgres_demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Разрешаем доступ к статическим ресурсам
                        .requestMatchers("/", "/login", "/register").permitAll() // Разрешаем доступ к общедоступным страницам
                        .requestMatchers("/pending-requests/new", "/pending-requests/{id}/create-request", "/requests/**", "/repairs/**").hasRole("ADMIN") // Доступ для администраторов
                        .requestMatchers("/statistics/**").hasAnyRole("ADMIN", "USER") // Доступ для пользователей и администраторов
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login") // Настройка пользовательской страницы логина
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Загружаем пользователя по имени из базы данных
            User user = userService.findByUsername(username);
            if (user != null) {
                // Создаем объект UserDetails на основе найденного пользователя
                return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().getName()) // Предполагаем, что роль также загружается с пользователем
                        .disabled(!user.isEnabled()) // Используем статус активности
                        .build();
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        };
    }
}
