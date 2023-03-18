package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;

import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RolesGenerator implements CommandLineRunner {

    final
    UserRepository userRepository;
    final
    UserService userService;

    @Autowired
    public RolesGenerator(RoleRepository roleRepository, UserRepository userRepository, UserService userService) {

        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void run(String... args) {
        System.out.println("Для проверки задачи можно пройти регистрацию. можно зарегестрировать как юзера, так и админа");
        Role adminrole = new Role("ROLE_ADMIN");
        Role userrole = new Role("ROLE_USER");

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();
        List<Role> roleUserAndAdmin = new ArrayList<>();
        roleAdmin.add(adminrole);
        roleUser.add(userrole);
        roleUserAndAdmin.add(adminrole);
        roleUserAndAdmin.add(userrole);

        userService.addUser(new User(roleAdmin, "admin","admin","Администратор","Администраторов","Администрандия",99,true));
        userService.addUser(new User(roleUser, "user","user","Юзер","Юзеров","Юзерляндия",1,true));
        userService.addUser(new User(roleUserAndAdmin, "dimrog","dimrog","Дмитрий","Рогожин","Россия",23,true));
        userService.addUser(new User(roleUser, "vaspetr","vaspetr","Василий","Петров","Беларусь",14,true));
        userService.addUser(new User(roleUser, "valalb","valalb","Валерий","Котов","Россия",54,true));

    }
}
