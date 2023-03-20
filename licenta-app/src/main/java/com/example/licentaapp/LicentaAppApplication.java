package com.example.licentaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ucv.ro.catalina.ilie.licenta.model")
public class LicentaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicentaAppApplication.class, args);
    }

}
