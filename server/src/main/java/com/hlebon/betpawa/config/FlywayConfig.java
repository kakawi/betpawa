package com.hlebon.betpawa.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public FlywayRunner flywayRunner(final Flyway flyway) {
        return new FlywayRunner(flyway);
    }

    @Bean
    public Flyway flyway(final DataSource source) {
        return Flyway.configure().dataSource(source).load();
    }
}
