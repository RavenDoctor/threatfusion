package com.adamshaddick.threatfusion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HealthResponse {

    private String status;
    private String application;
    private String version;
}