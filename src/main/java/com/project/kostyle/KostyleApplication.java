package com.project.kostyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KostyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KostyleApplication.class, args);
    }

}
