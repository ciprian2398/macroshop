package com.technetwork.macroshop.repository;

import com.technetwork.macroshop.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    @Query("from Credentials c where lower(c.username) = :username")
    Credentials findByUsername(@Param("username") String username);

}
