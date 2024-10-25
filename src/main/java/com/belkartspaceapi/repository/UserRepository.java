package com.belkartspaceapi.repository;

import com.belkartspaceapi.model.Client;
import com.belkartspaceapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUsername(String username);

    @Query("SELECT u.client FROM User u WHERE u.username = :username")
    Optional<Client> findClientByUsername(@Param("username") String username);
}
