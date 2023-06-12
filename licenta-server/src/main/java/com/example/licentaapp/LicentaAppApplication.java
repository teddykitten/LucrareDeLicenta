package com.example.licentaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@EntityScan("ucv.ro.catalina.ilie.licenta.model")
public class LicentaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicentaAppApplication.class, args);
    }
}
