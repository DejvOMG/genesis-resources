package com.genesis.repository;

import com.genesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPersonId(String personId);
    boolean existsByPersonId(String personId);
}
