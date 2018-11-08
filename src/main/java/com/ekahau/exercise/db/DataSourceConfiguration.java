package com.ekahau.exercise.db;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfiguration {
    private Logger log = LogManager.getLogger(DataSourceConfiguration.class);

    private static final String DB_SCRIPT_PATH = "classpath:exercise-db.sql";
    private static final String DB_DATA_SCRIPT_PATH = "classpath:exercise-db-data.sql";

    @Bean
    @Primary
    public DataSource datasource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript(DB_SCRIPT_PATH).addScript(DB_DATA_SCRIPT_PATH).build();
        log.info("DataSource initialized.");
        return dataSource;
    }
}