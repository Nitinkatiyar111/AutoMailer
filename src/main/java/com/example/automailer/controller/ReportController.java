package com.example.automailer.controller;

import com.example.automailer.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {


    private final ReportService reportService;

    // Example: http://localhost:8080/api/report/run
    @GetMapping("/run")
    public String runReport() {
        reportService.sendReport();
        return "Report generation triggered successfully.";
    }
}
