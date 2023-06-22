package com.MovieApps.service;

import org.springframework.stereotype.Service;

import com.MovieApps.model.User;
import com.MovieApps.repo.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.findByLoginId(user.getLoginId()) != null) {
            throw new IllegalArgumentException("Login ID is already taken");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email is already registered");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        return userRepository.save(user);
    }

    public User login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User resetPassword(String loginId, String newPassword) {
        User user = userRepository.findByLoginId(loginId);
        if (user != null) {
            user.setPassword(newPassword);
            user.setConfirmPassword(newPassword);
            
            return userRepository.save(user);
        }
        return null;
    }
  
}
