package com.lfernandes;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(DataInitializer.class);

    private final JdbcTemplate jdbcTemplate;
    private final Environment environment;

    public DataInitializer(JdbcTemplate jdbcTemplate, Environment environment) {
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] activeProfiles = environment.getActiveProfiles();
        logger.info("Perfis ativos: " + String.join(", ", activeProfiles));

        // Verificação e log do banco de dados ativo
        logger.info("Verificando o banco de dados ativo...");
        try {
            String databaseProductName = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
            logger.info("Banco de dados ativo: " + databaseProductName);
        } catch (Exception e) {
            logger.error("Erro ao verificar o banco de dados ativo: ", e);
        }

        if (java.util.Arrays.asList(activeProfiles).contains("h2")) {
            logger.info("Executando arquivo data-h2.sql para perfil 'h2'...");

            try {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data-h2.sql");
                if (inputStream != null) {
                    String sql = new BufferedReader(new InputStreamReader(inputStream))
                            .lines().collect(Collectors.joining("\n"));
                    jdbcTemplate.execute(sql);
                    logger.info("Arquivo data-h2.sql executado com sucesso.");
                } else {
                    logger.error("Arquivo data-h2.sql não encontrado.");
                }
            } catch (Exception e) {
                logger.error("Erro ao executar o arquivo data-h2.sql: ", e);
            }
        }

    }
}
