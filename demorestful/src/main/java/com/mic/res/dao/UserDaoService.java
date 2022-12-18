package com.mic.res.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.mic.res.constant.Constant;
import com.mic.res.entity.User;

@Component
public class UserDaoService {
    private static int userCount = 0;
    private static List<User> users = new ArrayList<User>();
    static {
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Charith",
                convertToLocalDateViaInstant(new Date())));
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Madusha",
                convertToLocalDateViaInstant(new Date())));
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Jenuli",
                convertToLocalDateViaInstant(new Date())));
    }

    public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        LocalDateTime ldt = LocalDateTime.ofInstant(dateToConvert.toInstant(),
                ZoneId.systemDefault());
        return ldt;
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(BigDecimal id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR));
        users.add(user);
        return user;
    }

    public void deleteById(BigDecimal id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
