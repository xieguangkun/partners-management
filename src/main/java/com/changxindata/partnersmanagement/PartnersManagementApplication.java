package com.changxindata.partnersmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PartnersManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(PartnersManagementApplication.class, args);
    }

}
