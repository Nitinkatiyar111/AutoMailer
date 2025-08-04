package com.example.automailer.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "report")
@Getter
@Setter
public class ExtractionQueryConfig {

    private String outputPath;
    private Map<String,String> queries;
}
