package com.test.restful_users.service;

import com.test.restful_users.model.User;
import com.test.restful_users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public boolean deleteUserById(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserById(String id, User user) {
        Optional<User> foundUserInDB = userRepository.findById(id);
        if (foundUserInDB.isPresent()) {
            User foundUser = foundUserInDB.get();
            if (user.getId() != null)
                foundUser.setId(user.getId());
            if (user.getName() != null)
                foundUser.setName(user.getName());
            if (user.getEmail() != null)
                foundUser.setEmail(user.getEmail());
            userRepository.save(foundUser);
            return true;
        } else {
            return false;
        }
    }
}
