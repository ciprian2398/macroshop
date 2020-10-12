package com.technetwork.macroshop.dao.impl;

import com.technetwork.macroshop.dao.CredentialsDao;
import com.technetwork.macroshop.model.Credentials;
import com.technetwork.macroshop.model.enums.CredentialsRole;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsDaoImpl implements CredentialsDao {

    @Override
    public Credentials findByUsername(String username) {
        //todo   return credentialsRepository.findByUsername(username.toLowerCase());
        return new Credentials(
                username,
                /*wasd*/"$2y$12$ilj5slREZE9MeiLKZQb8x.ZRHIPjDKEeyYQ9E6xhLJ8yXJX12kOse",
                CredentialsRole.ROLE_USER);
    }
}
