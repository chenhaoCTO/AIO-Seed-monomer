package com.seed.ninomer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seed.ninomer.core.mapper.*")
public class NinomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinomerApplication.class, args);
    }

}
