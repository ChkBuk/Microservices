package com.mic.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

/**
 * @author charith
 * This class is Base model for the generic entities 
 */

public abstract class BaseModel extends RepresentationModel<BaseModel> implements Serializable {
  
    private BigDecimal id;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

}