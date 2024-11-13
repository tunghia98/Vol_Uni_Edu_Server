package com.j2ee.vol_uni_edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VolUniEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolUniEduApplication.class, args);
    }

}
