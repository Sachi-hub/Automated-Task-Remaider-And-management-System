package com.mywaysai.automatedtaskremindertrackingapplication.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mywaysai.automatedtaskremindertrackingapplication.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;
    public ReportController(ReportService reportService) { this.reportService = reportService; }

    @GetMapping("/overview")
    public ResponseEntity<?> overview() {
        return ResponseEntity.ok(reportService.overview());
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportCsv() throws Exception {
        String csv = reportService.exportAllTasksCsv();
        byte[] bytes = csv.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tasks_report.csv");
        return ResponseEntity.ok().headers(headers).body(bytes);
    }
}

