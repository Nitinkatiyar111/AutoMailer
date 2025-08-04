package com.example.automailer.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

@Component
public class FileWriterUtil {

    public void writeToExcel(Map<String, Integer> resultMap, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Query Results");

            // Header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Table_Name");
            header.createCell(1).setCellValue("Count");

            // Data
            int rowIdx = 1;
            for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            // Create directory if needed
            Path parentDir = Paths.get(filePath).getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing Excel file", e);
        }
    }
}
