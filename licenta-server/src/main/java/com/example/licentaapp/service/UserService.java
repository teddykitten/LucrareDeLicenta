package com.example.licentaapp.service;

import com.example.licentaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucv.ro.catalina.ilie.licenta.model.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User registerNewUser(User user){
        return userRepository.save(user);

    }
}
