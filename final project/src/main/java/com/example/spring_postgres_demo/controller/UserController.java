package com.example.spring_postgres_demo.controller;

import com.example.spring_postgres_demo.dao.role.RoleRepository;
import com.example.spring_postgres_demo.model.User;
import com.example.spring_postgres_demo.dao.user.UserRepository;
import com.example.spring_postgres_demo.service.role.RoleService;
import com.example.spring_postgres_demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Возвращает шаблон Thymeleaf или другой вид страницы логина
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // Добавляем пустой объект User в модель
        return "register"; // Возвращает шаблон Thymeleaf или другой вид страницы регистрации
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        // Кодируем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName("USER"));
        user.setEnabled(true); // Или установите флаг в зависимости от ваших требований
        userService.save(user); // Сохраняем пользователя в базе данных
        return "redirect:/login"; // Перенаправляем на страницу логина после успешной регистрации
    }

    @GetMapping("/logout")
    public String logout() {
        // Логика выхода может быть обработана Spring Security
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
