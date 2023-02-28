package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findUserByUsername(String username);

    User findUserById(int id);

    Optional<User> findUserByEmail(String email);
}
