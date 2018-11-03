package ru.blinov.mygeekspringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.blinov.mygeekspringboot.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String theRoleName);
}
