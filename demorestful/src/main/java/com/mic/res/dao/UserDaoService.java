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
import com.mic.res.entity.Name;
import com.mic.res.entity.User;
import com.mic.res.entity.UserV2;

@Component
public class UserDaoService {
    private static int userCount = 0;
    private static List<User> users = new ArrayList<User>();
    private static List<UserV2> users_v2 = new ArrayList<UserV2>();
    static {
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Charith",
                convertToLocalDateViaInstant(new Date())));
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Madusha",
                convertToLocalDateViaInstant(new Date())));
        users.add(new User(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR), "Jenuli",
                convertToLocalDateViaInstant(new Date())));

        users_v2.add(new UserV2(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR),
                new Name("Charith", "Buddika"),
                convertToLocalDateViaInstant(new Date())));

        users_v2.add(new UserV2(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR),
                new Name("Janaka", "Manju"),
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

    public List<UserV2> findAll_V2() {
        return users_v2;
    }

    public User findById(BigDecimal id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public UserV2 findById_V2(BigDecimal id) {
        Predicate<? super UserV2> predicate = user -> user.getId().equals(id);
        return users_v2.stream().filter(predicate).findFirst().orElse(null);
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
