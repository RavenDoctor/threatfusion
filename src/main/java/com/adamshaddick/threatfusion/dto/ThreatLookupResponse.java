package com.adamshaddick.threatfusion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThreatLookupResponse {

    private String ipAddress;
    private Integer threatScore;
    private String country;
    private String riskLevel;
}