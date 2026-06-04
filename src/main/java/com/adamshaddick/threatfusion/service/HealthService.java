package com.adamshaddick.threatfusion.service;

import com.adamshaddick.threatfusion.dto.HealthResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public HealthResponse getHealth() {
        return new HealthResponse(
                "ONLINE",
                "ThreatFusion",
                "1.0"
        );
    }
}