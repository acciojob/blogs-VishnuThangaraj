package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        //Set firstName and LastName as test by default
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");

        userRepository3.save(user); // Add to Database
        return user;
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId); // Delete user by ID
    }

    public User updateUser(Integer id, String password){
        Optional<User> users = userRepository3.findById(id);
        User user = null;

        if(users.isPresent()){
            user = users.get();
            user.setPassword(password); // Update password
            userRepository3.save(user);
        }

        return user;
    }
}
