package com.hlebon.betpawa.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;

public class FlywayRunner implements CommandLineRunner {

    private final Flyway flyway;

    public FlywayRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void run(String... args) throws Exception {
        flyway.migrate();
    }
}
