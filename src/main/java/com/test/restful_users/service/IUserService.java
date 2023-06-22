package com.test.restful_users.service;

import com.test.restful_users.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    boolean deleteUserById(String id);

    boolean updateUserById(String id, User user);
}
