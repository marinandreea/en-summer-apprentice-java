package com.endava.practica.service;

import com.endava.practica.model.User;
import com.endava.practica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        userIterable.forEach(users::add);
        return users;
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
}
