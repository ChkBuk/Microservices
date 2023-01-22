package com.mic.res.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mic.res.dao.UserDaoService;
import com.mic.res.entity.User;
import com.mic.res.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@ExposesResourceFor(User.class)
public class UserController {
    @Autowired
    private UserDaoService userDao;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private EntityLinks entityLinks;

    private EntityModel entityModel;

    @GetMapping(path = "/users")
    public MappingJacksonValue getAllUsers() {
        List<User> users = userDao.findAll();
        if(users == null || users.size()==0){
            throw new UserNotFoundException("No data found.");
        }
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDao.findAll());
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "user_name", "birth_date");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

 
    @GetMapping(path = "/users/{id}")
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

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable BigDecimal id) {
        userDao.deleteById(id);
    }

    @GetMapping(path = "/greet")
    public String getGreetingMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
