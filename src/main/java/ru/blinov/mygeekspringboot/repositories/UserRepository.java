package ru.blinov.mygeekspringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.blinov.mygeekspringboot.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}
