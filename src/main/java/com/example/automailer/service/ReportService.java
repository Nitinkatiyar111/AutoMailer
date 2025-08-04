package com.example.automailer.service;

import com.example.automailer.config.ExtractionQueryConfig;
import com.example.automailer.util.FileWriterUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final JdbcTemplate jdbcTemplate;
    private final ExtractionQueryConfig extractionQueryConfig;
    private final FileWriterUtil fileWriterUtil;

    @Scheduled(cron = "0 0 7 * * *")
    public void generateReport() {
        Map<String, Integer> resultMap = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : extractionQueryConfig.getQueries().entrySet()) {
            String label = entry.getKey();
            String sql = entry.getValue();

            try {
                Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
                resultMap.put(label, count);
            } catch (Exception e) {
                resultMap.put(label, -1); // Error fallback
            }
        }

        fileWriterUtil.writeToExcel(resultMap, extractionQueryConfig.getOutputPath());
        System.out.println("Excel report generated: " + extractionQueryConfig.getOutputPath());
    }
    @PostConstruct
    public void triggerReportOnStartup() {
        System.out.println("Triggering report at startup...");
        generateReport();
    }
}
