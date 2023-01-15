package com.mic.res.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.mic.res.entity.User;

@Repository
public class UserJDBCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String INSER_SQL = """
            Insert into Users(id,name,password,dob)
            values(
            ?,?,?,?
            );
                """;

    private String DELETE_SQL = """
            Delete from Users where id = ?;
                 """;
    private String SELECT_SQL = """
            Select * from Users where id = ?;
                 """;
    private String SELECT_ALL_SQL = """
            Select * from Users;
                 """;

    public void insert(User user) {
        jdbcTemplate.update(INSER_SQL, user.getId(), user.getName(), user.getPassword(),
                user.getDob().toString().substring(0, 10));
    }

    public void delete(BigDecimal id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public User fetchById(BigDecimal id) {
        return jdbcTemplate.queryForObject(SELECT_SQL, new BeanPropertyRowMapper<>(User.class), id);
    }

    public List<User> fetchAll() {
        List<User> users = jdbcTemplate.query(SELECT_ALL_SQL, new UserRowMapper());
        return users;
    }

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getBigDecimal("ID"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setDob(rs.getTimestamp("dob").toLocalDateTime());
            return user;
        }
    }
}
