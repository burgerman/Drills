package com.wil.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan(basePackages = "com.wil.practice.mapper")
@ComponentScan(basePackages = "com.wil.practice.*")
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

}
