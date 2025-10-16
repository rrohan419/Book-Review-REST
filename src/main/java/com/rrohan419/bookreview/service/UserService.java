package com.rrohan419.bookreview.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.rrohan419.bookreview.dto.AuthRequest;
import com.rrohan419.bookreview.dto.AuthResponse;
import com.rrohan419.bookreview.dto.SignupUser;
import com.rrohan419.bookreview.model.User;
import com.rrohan419.bookreview.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SignupUser signupUser) {

        User user = new User(signupUser.getUserName().toLowerCase(), Set.of("READER"), signupUser.getPassword());

        return userRepository.save(user);
    }

    public AuthResponse loginUser(AuthRequest authRequest) {
        User user = userRepository.findByNameAndPassword(authRequest.getUsername().toLowerCase(), authRequest.getPassword());
        if (user == null) {
            throw new RuntimeException("invalid creds");
        }

        Set<String> roles = user.getRoles();
        if (roles.contains("ADMIN")) {
            return new AuthResponse("token-admin", user.getRoles(), user.getId(), user.getName());
        } else if (roles.contains("READER")) {  
            return new AuthResponse("token-reader", user.getRoles(), user.getId(), user.getName());
        } else {
            return null;
        }
    }
}
