package com.adamshaddick.threatfusion.controller;

import com.adamshaddick.threatfusion.entity.ThreatLookupHistory;
import com.adamshaddick.threatfusion.service.ThreatLookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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