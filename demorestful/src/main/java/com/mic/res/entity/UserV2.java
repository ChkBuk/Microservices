package com.mic.res.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class UserV2 {
    private BigDecimal id;
    @Size(min=2, message = "Name must be longer than 2 characters")
    private Name name;
    @Past(message="Birth date should not be a future date")
    private LocalDateTime dob;

    public UserV2(BigDecimal id, Name name, LocalDateTime localDate) {
        super();
        this.id = id;
        this.name = name;
        this.dob = localDate;
    }


    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public LocalDateTime getDob() {
        return dob;
    }
    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }
}
