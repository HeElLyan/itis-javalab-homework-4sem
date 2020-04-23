package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
