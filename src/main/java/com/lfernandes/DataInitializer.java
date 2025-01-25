package com.lfernandes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE users (username VARCHAR(50) PRIMARY KEY, password VARCHAR(50))");
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('usuario', 'senha')");
    }
}
