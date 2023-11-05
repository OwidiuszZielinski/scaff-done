package com.dev.scaffdone;

import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaRepositories
@SpringBootApplication()
public class ScaffDoneApplication implements CommandLineRunner {

    private final ScaffoldingService service;

    public ScaffDoneApplication(ScaffoldingService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScaffDoneApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        service.initTestData();
    }
}
