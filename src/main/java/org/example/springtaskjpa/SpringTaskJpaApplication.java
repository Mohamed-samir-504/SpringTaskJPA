package org.example.springtaskjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringTaskJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskJpaApplication.class, args);
    }

}
