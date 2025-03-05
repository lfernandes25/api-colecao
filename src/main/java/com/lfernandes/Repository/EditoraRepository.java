package com.lfernandes.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EditoraRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EditoraRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Cadastra editora
    public void insertEditora(String nomeEditora) {
        String sql = "INSERT INTO editoras (NOME_EDITORA) VALUES (?)";
        jdbcTemplate.update(sql, nomeEditora);
    }

    //Consultar se já existe cadastro
    public boolean existsByNomeEditora(String nomeEditora) {
        String sql = "SELECT COUNT(*) FROM editoras WHERE NOME_EDITORA = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nomeEditora);
        return count != null && count > 0;
    }
}
