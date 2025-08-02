package com.example.automailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.automailer.service.ReportService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class AutomailerApplication  {


    public static void main(String[] args) {
        SpringApplication.run(AutomailerApplication.class, args);
    }

    }
