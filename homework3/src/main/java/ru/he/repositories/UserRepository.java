package ru.he.repositories;

import org.springframework.stereotype.Repository;
import ru.he.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Long, User> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmId(String confirmedId);

    List<User> findAllConfirmed();

    List<User> findAllUnconfirmed();

}
