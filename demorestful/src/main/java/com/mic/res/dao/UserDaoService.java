package com.mic.res.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mic.res.constant.Constant;
import com.mic.res.entity.User;
import com.mic.res.entity.UserV2;
import com.mic.res.repository.UserJDBCRepository;

@Component
public class UserDaoService {
    @Autowired
    private UserJDBCRepository userRepository;
    private static int userCount = 0;
    private static List<UserV2> users_v2 = new ArrayList<UserV2>();


    public static LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        LocalDateTime ldt = LocalDateTime.ofInstant(dateToConvert.toInstant(),
                ZoneId.systemDefault());
        return ldt;
    }

    public List<User> findAll() {
        return userRepository.fetchAll();
    }

    public List<UserV2> findAll_V2() {
        return users_v2;
    }

    public User findById(BigDecimal id) {
        return userRepository.fetchById(id);
    }

    public UserV2 findById_V2(BigDecimal id) {
        Predicate<? super UserV2> predicate = user -> user.getId().equals(id);
        return users_v2.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(new BigDecimal(++userCount * Constant.ID_MULTIPICATION_FACTOR));
        userRepository.insert(user);
        return user;
    }

    public void deleteById(BigDecimal id) {
        userRepository.delete(id);
    }
}
