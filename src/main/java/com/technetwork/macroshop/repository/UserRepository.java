package com.technetwork.macroshop.repository;

import com.technetwork.macroshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User c where lower(c.login) = :login")
    User findByLogin(@Param("login") String login);

}
