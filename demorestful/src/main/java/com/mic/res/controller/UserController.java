package com.mic.res.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mic.res.dao.UserDaoService;
import com.mic.res.entity.User;
import com.mic.res.exception.UserNotFoundException;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userDao;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable BigDecimal id) {
        User user = userDao.findById(id);
        if(user == null){
            throw new UserNotFoundException("Id:"+id);
        }
            
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}