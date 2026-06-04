package com.adamshaddick.threatfusion.controller;

import com.adamshaddick.threatfusion.dto.ThreatLookupResponse;
import com.adamshaddick.threatfusion.service.ThreatLookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ThreatLookupController {

    private final ThreatLookupService threatLookupService;

    public ThreatLookupController(
            ThreatLookupService threatLookupService) {

        this.threatLookupService = threatLookupService;
    }

    @GetMapping("/api/search/ip/{ipAddress}")
    public ThreatLookupResponse lookupIp(
            @PathVariable String ipAddress) {

        return threatLookupService.lookupIp(ipAddress);
    }
}