package com.example.web4.user;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity, String> {
    @Query("SELECT * FROM users WHERE login = :username")
    Optional<UserEntity> existsByUsername(String username);

    @Query("SELECT * FROM users WHERE login = :username AND password = :password")
    Optional<UserEntity> checkUserLogin(String username, String password);

    @Modifying
    @Query("INSERT INTO users(login, password) VALUES(:login, :password)")
    public void registerUser(@Param("login") String login, @Param("password") String password);
}
