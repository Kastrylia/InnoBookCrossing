package com.example.innobookcrossing.controller;

import com.example.innobookcrossing.exceptions.AlreadyExistException;
import com.example.innobookcrossing.jpa.UserRepository;
import com.example.innobookcrossing.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    @CrossOrigin(origins = "http://localhost:3000")
    public String singIn(@RequestBody User user) throws NotFoundException {
        if (userRepository.findUserByAlias(user.getAlias()) == null) {
            throw new NotFoundException("User with such alias does not exist.");
        } else {
            if (userRepository.findUserByAlias(user.getAlias()).getPassword().equals(user.getPassword())) {
                return "User sing in successfully";
            } else {
                throw new NotFoundException("User with such password does not exist.");
            }
        }
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public User register(@RequestBody User user) {
        if (userRepository.findUserByAlias(user.getAlias()) == null) {
            return userRepository.save(user);
        } else throw new AlreadyExistException("User already exists.");
    }

}
