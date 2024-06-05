package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "entity")
@SpringBootApplication(scanBasePackages = {"controllers", "services", "repositories", "security", "config"})
public class Main {
    public static void main(String[] args) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //System.out.println(encoder.encode("123"));
        SpringApplication.run(Main.class, args);
    }
}