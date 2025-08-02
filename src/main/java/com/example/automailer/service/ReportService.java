package com.example.automailer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    private final JavaMailSender mailSender;

    @Value("${report.query}")
    private String reportQuery;

    @Value("${mail.info.sendTo}")
    private String mailTo;

    @Value("${mail.info.cc}")
    private String mailCc;

    public void sendReport() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(reportQuery);
        StringBuilder sb = new StringBuilder("Report Output:\n\n");

        for (Map<String, Object> row : rows) {
            sb.append(row).append("\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setCc(mailCc);
        message.setSubject("Report Output - COS_SM");
        message.setText(sb.toString());

        mailSender.send(message);
    }
}
