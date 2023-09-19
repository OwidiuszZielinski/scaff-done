package com.dev.scaffdone;

import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import com.dev.scaffdone.scaffold.entity.Size;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@SpringBootApplication()
public class ScaffDoneApplication implements CommandLineRunner {


    public static void main(String[] args) {


        SpringApplication.run(ScaffDoneApplication.class, args);


    }


    @Override
    public void run(String... args) throws Exception {

    }
}
