package ru.blinov.mygeekspringboot.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.blinov.mygeekspringboot.entities.SystemUser;
import ru.blinov.mygeekspringboot.entities.User;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}
