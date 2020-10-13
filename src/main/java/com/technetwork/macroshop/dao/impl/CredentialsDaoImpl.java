package com.technetwork.macroshop.dao.impl;

import com.technetwork.macroshop.dao.CredentialsDao;
import com.technetwork.macroshop.model.User;
import com.technetwork.macroshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CredentialsDaoImpl implements CredentialsDao {

    private final UserRepository userRepository;

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login.toLowerCase());
    }
}
