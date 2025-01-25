package com.lfernandes;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private UserRepository userRepository = new UserRepository();

    public boolean authenticate(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        return foundUser != null && foundUser.getPassword().equals(user.getPassword());
    }
}
