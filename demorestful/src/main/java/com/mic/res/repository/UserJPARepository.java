package com.mic.res.repository;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.mic.res.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserJPARepository {
    @PersistenceContext
    private EntityManager entityManager; 

    public void insert(User user){
        entityManager.merge(user);
    }
    public User fetchById(BigDecimal id) {
        return entityManager.find(User.class, id);
    }

    public void delete(BigDecimal id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
