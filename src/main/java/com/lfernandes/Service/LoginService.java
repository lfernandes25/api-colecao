package com.lfernandes.Service;

import com.lfernandes.Class.User;
import com.lfernandes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser != null && foundUser.getPassword().equals(user.getPassword());
    }
}
