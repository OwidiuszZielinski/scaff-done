package com.dev.scaffdone;

import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.user.Role;
import com.dev.scaffdone.core.user.User;
import com.dev.scaffdone.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Set;

@EnableJpaRepositories
@RequiredArgsConstructor
@SpringBootApplication()
public class ScaffDoneApplication implements CommandLineRunner {

    private final ScaffoldingService service;
    private final UserRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(ScaffDoneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.initTestData();
        User save = repository.save(User.builder()
                .email("owidiusz")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles(Set.of(Role.ADMIN))
                .login("user").build());
        System.out.println(save);

    }
}
