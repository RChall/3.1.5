package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Table(name = "users")
@Entity
public class User implements UserDetails {
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();

    public void addRole(String role) {
        roles.add(new Role(role));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String lastName;
    private String country;
    private Integer age;
    private Boolean enabled;

    public User() {
    }


    public User(List<Role> roles, String username, String password, String name, String lastName, String country, Integer age) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public User(List<Role> roles, String username, String password, String name, String lastName, String country, Integer age, Boolean enabled) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
        this.enabled = enabled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getRole() {
        List<String> strRoles = roles.stream().map(Role::toString).collect(Collectors.toList());
        if (roles.size() == 2) {
            return strRoles.get(0) + "\n"
                   + strRoles.get(1);
        } else {
            return strRoles.get(0);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String toString() {
        return id + ") " + name + " " + lastName + ", " + country + ", " + age;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(roles.toString()));
    }


    //не просрочен
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //пароль не просрочен
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //работающий ли аккаунт
    @Override
    public boolean isEnabled() {
        return true;
    }
}

