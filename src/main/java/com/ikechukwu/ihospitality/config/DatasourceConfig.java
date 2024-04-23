package com.ikechukwu.ihospitality.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
@Slf4j
public class DatasourceConfig {

    @Bean
    public CommandLineRunner initData(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                String sqlScriptContent = new String(Files.readAllBytes(Paths.get("src/main/resources/patients.sql")));
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sqlScriptContent);
                }
                log.info("Patients data populated successfully!");
            } catch (IOException e) {
                log.info("Error reading SQL script: {}", e.getMessage());
            }
        };
    }
}
