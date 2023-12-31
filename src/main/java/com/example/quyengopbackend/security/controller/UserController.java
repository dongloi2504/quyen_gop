package com.example.quyengopbackend.security.controller;

import com.example.quyengopbackend.security.exception.SignupFailedException;
import com.example.quyengopbackend.security.exception.UserNotFoundException;
import com.example.quyengopbackend.security.model.User;
import com.example.quyengopbackend.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/security")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws SignupFailedException {
        Optional<User> user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1.isPresent()) {
            throw new SignupFailedException("User already existed");
        }
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) throws UserNotFoundException {
        Optional<User> user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1.isPresent()) {
            if (user1.get().getPassword().equals(user.getPassword())) {
                return user1.get();
            } else {
               throw new UserNotFoundException("wrong input username or password");
            }
        }
        throw new UserNotFoundException("wrong input username or password");
    }

    @GetMapping("/currentUser")
    public User getCurrentUser(@RequestParam String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestBody User user, @RequestParam String username) {
        User currentUser = userRepository.findUserByUsername(username).orElse(null);
        if (currentUser != null) {
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            userRepository.save(currentUser);
        }
        return currentUser;
    }
}

