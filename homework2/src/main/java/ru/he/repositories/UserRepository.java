package ru.he.repositories;

import ru.he.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmId(String confirmedId);

    List<User> findAll();

    List<User> findAllConfirmed();

    List<User> findAllUnconfirmed();

}
