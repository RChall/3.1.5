package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Component
public class RolesGenerator implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) {
        System.out.println("Для проверки задачи можно пройти регистрацию. можно зарегестрировать как юзера, так и админа");
        Role adminrole = new Role("ROLE_ADMIN");
        Role userrole = new Role("ROLE_USER");
      roleRepository.save(adminrole);
      roleRepository.save(userrole);



    }
}
