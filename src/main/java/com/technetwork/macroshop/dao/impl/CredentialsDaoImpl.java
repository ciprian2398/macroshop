package com.technetwork.macroshop.dao.impl;

import com.technetwork.macroshop.dao.CredentialsDao;
import com.technetwork.macroshop.model.Credentials;
import com.technetwork.macroshop.repository.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CredentialsDaoImpl implements CredentialsDao {

    private final CredentialsRepository credentialsRepository;

    @Override
    public Credentials findByUsername(String username) {
        return credentialsRepository.findByUsername(username.toLowerCase());
    }
}
