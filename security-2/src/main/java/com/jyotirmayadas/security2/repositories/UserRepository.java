package com.jyotirmayadas.security2.repositories;

import com.jyotirmayadas.security2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
