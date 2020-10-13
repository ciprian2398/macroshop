package com.technetwork.macroshop.dao;

import com.technetwork.macroshop.model.User;

public interface CredentialsDao {
    User findByLogin(String username);
}
