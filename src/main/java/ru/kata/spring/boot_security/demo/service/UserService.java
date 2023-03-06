package ru.kata.spring.boot_security.demo.service;





import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    List<User> getAllUsers();
    public User getUserById(Long id);
    void deleteUserById(Long id);

    public void updateUser(User user);
    public List<Role> findAll();
    User findByUsername(String username);

}
