package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u.username, u.password FROM User u WHERE u.username = :username")
    Optional<Object[]> findPasswordByUsername(@Param("username") String username);




    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}