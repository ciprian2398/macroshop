package com.technetwork.macroshop.dao;

import com.technetwork.macroshop.model.User;

public interface UserDao {

    User findByLogin(String login);

}
