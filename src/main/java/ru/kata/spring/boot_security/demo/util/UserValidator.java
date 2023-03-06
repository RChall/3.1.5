package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.entity.User;

import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userDtlService;

    public UserValidator(UserService userDtlService) {
        this.userDtlService = userDtlService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDtlService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "","Пользователь с таким логином уже существует");
    }
}
