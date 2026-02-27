package com.example.snb2user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.snb2user.entity.User;
import com.example.snb2user.repository.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User register(User user) {
        return repo.save(user);
    }
    public List<User> getAllUsers(){
        return repo.findAll();    
    }

    public User login(String username, String password) {
        User user = repo.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}