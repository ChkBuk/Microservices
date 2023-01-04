package com.mic.res.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
//@JsonIgnoreProperties({"password","birth_date"})
@JsonFilter("UserFilter")
public class User {
    private BigDecimal id;
    @Size(min=2, message = "Name must be longer than 2 characters")
    @JsonProperty("user_name")
    private String name;
    @Past(message="Birth date should not be a future date")
    @JsonProperty("birth_date")
    private LocalDateTime dob;
    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public User(BigDecimal id, String name, LocalDateTime localDate, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = localDate;
    }

    public String getName() {
        return name;
    }

   
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", password=" + password + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }
    
}
