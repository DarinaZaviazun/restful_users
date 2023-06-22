package com.test.restful_users.controller;

import com.test.restful_users.model.User;
import com.test.restful_users.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
class UserControllerTest {
    @Autowired
    UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Daryna");
        testUser.setEmail("daryna@gmail.com");
        String id = userService.saveUser(testUser);
        testUser.setId(id);
    }

    @AfterEach
    void tearDown() {
        for (User user : userService.getAllUsers()) {
            userService.deleteUserById(user.getId());
        }
    }
    @Test
    void getUsers() {
        List<User> allUsers = userService.getAllUsers();

        assertEquals(1, allUsers.size());
    }

    @Test
    void getUserById() {
        Optional<User> userById = userService.getUserById(testUser.getId());

        assertEquals(testUser, userById.get());
    }

    @Test
    void createNewUser() {
        User newUser = new User();
        newUser.setName("Margo");
        newUser.setEmail("margo@gmail.com");
        String id = userService.saveUser(newUser);
        newUser.setId(id);

        assertEquals(2, userService.getAllUsers().size());
        assertEquals(newUser, userService.getUserById(id).get());
    }

    @Test
    void updateUser() {
        User userUpdate = new User();
        userUpdate.setName("Alex");
        userService.updateUserById(testUser.getId(), userUpdate);
        Optional<User> userById = userService.getUserById(testUser.getId());

        assertEquals(userUpdate.getName(), userById.get().getName());
    }

    @Test
    void deleteUser() {
        boolean deleteUserByNonExistedId = userService.deleteUserById("nonexistingid");
        assertFalse(deleteUserByNonExistedId);

        boolean deleteUserByExistedId = userService.deleteUserById(testUser.getId());
        assertTrue(deleteUserByExistedId);
    }
}