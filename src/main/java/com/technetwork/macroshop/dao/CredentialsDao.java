package com.technetwork.macroshop.dao;

import com.technetwork.macroshop.model.Credentials;

public interface CredentialsDao {
    Credentials findByUsername(String username);
}
