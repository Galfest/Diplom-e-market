package ru.abramyants.emarket.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.abramyants.emarket.auth.services.UserService;
import ru.abramyants.emarket.api.RegisterUserDto;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void registrateNewUser(@RequestBody RegisterUserDto registerUserDto) {
        // TODO полностью реализовать метод, как считаете нужным
        //  ниже всего лишь пример хеширования паролей
        String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
    }
}
