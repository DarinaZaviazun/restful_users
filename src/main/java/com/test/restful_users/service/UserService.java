package com.test.restful_users.service;

import com.test.restful_users.model.User;
import com.test.restful_users.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private IUserRepository IUserRepository;

    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    public String saveUser(User user) {
        User savedUser = IUserRepository.save(user);
        return savedUser.getId();
    }

    public List<User> getAllUsers() {
        return IUserRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        if (isIdIncorrect(id)) {
            return Optional.empty();
        }
        return IUserRepository.findById(id);
    }

    public boolean deleteUserById(String id) {
        if (isIdIncorrect(id)) {
            return false;
        }
        if (IUserRepository.findById(id).isPresent()) {
            IUserRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateUserById(String id, User user) {
        if (isIdIncorrect(id)) {
            return false;
        }

        Optional<User> foundUserInDB = IUserRepository.findById(id);
        if (foundUserInDB.isPresent()) {
            User foundUser = foundUserInDB.get();
            if (user.getId() != null)
                foundUser.setId(user.getId());
            if (user.getName() != null)
                foundUser.setName(user.getName());
            if (user.getEmail() != null)
                foundUser.setEmail(user.getEmail());
            IUserRepository.save(foundUser);
            return true;
        } else {
            return false;
        }
    }

    private boolean isIdIncorrect(String id) {
        return id == null || id.isBlank() || id.isEmpty();
    }
}
