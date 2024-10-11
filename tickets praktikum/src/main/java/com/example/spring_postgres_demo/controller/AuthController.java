package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.model.User;
import com.example.spring_postgres_demo.service.roleservice.RoleService;
import com.example.spring_postgres_demo.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Возвращаем страницу логина
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // Добавляем пустой объект User в модель
        return "register"; // Возвращаем шаблон Thymeleaf для страницы регистрации
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "register"; // Возврат на страницу регистрации с сообщением об ошибке
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Кодируем пароль
        user.setRole(roleService.findByName("USER")); // Устанавливаем роль по умолчанию
        user.setEnabled(true); // Устанавливаем пользователя как активного
        userService.save(user); // Сохраняем пользователя в базе данных
        System.out.println("Registering user: " + user.getUsername());
        return "redirect:/login"; // Перенаправляем на страницу логина после успешной регистрации
    }

    @GetMapping("/logout")
    public String logout() {
        // Логика выхода может быть обработана Spring Security
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
