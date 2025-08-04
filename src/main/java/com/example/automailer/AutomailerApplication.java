package com.example.automailer;

import com.example.automailer.config.ExtractionQueryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(ExtractionQueryConfig.class)
public class AutoMailerApplication  {


    public static void main(String[] args) {

        SpringApplication.run(AutoMailerApplication.class, args);
    }

    }
