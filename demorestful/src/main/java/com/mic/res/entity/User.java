package com.mic.res.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private BigDecimal id;
    private String name;
    private LocalDateTime dob;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public User(BigDecimal id, String name, LocalDateTime localDate) {
        super();
        this.id = id;
        this.name = name;
        this.dob = localDate;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
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
