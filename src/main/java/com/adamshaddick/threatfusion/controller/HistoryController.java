package com.adamshaddick.threatfusion.controller;

import com.adamshaddick.threatfusion.entity.ThreatLookupHistory;
import com.adamshaddick.threatfusion.service.ThreatLookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HistoryController {

    private final ThreatLookupService threatLookupService;

    public HistoryController(
            ThreatLookupService threatLookupService) {

        this.threatLookupService = threatLookupService;
    }

    @GetMapping("/api/history")
    public List<ThreatLookupHistory> getHistory() {
        return threatLookupService.getHistory();
    }
}