package com.lfernandes.Repository;

import com.lfernandes.Class.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (resultSet, rowNum) ->
                    new User(resultSet.getString("username"), resultSet.getString("password"))
            );
        } catch (EmptyResultDataAccessException e) {
            // Tratar o caso em que nenhum usuário foi encontrado
            return null; // Ou lançar uma exceção personalizada
        }
    }
}

