package com.mic.res.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.mic.res.dao.UserDaoService;
import com.mic.res.entity.User;
import com.mic.res.entity.UserV2;
import com.mic.res.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userDao;
    @Autowired
    private MessageSource messageSource;
    private EntityModel entityModel;
   

    @GetMapping(path = "/users/accept", produces ="application/abc.company.app-v1+json")
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
    @GetMapping(path = "/users/accept", produces ="application/abc.company.app-v2+json")
    public List<UserV2> getAllUsers_V2() {
        return userDao.findAll_V2();
    }
    @GetMapping(path = "/users/{id}", params="version=1")
    public EntityModel<User> getUserById(@PathVariable BigDecimal id) {
        User user = userDao.findById(id);
        if(user == null){
            throw new UserNotFoundException("User Id:"+id+" is not available in the system, Please re-check the user id.");
        }
        entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @GetMapping(path = "/users/{id}", params="version=2")
    public UserV2 getUserById_V2(@PathVariable BigDecimal id) {
        UserV2 user = userDao.findById_V2(id);
        if(user == null){
            throw new UserNotFoundException("User Id:"+id+" is not available in the system, Please re-check the user id.");
        }
            
        return user;
    }
    @PostMapping(path = "/users", params="version=1")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping(path = "/users/{id}", params="version=1")
    public void deleteUserById(@PathVariable BigDecimal id) {
        userDao.deleteById(id);
    }

    @GetMapping(path = "/greet", params="version=1")
    public String getGreetingMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale); 
    }
}
