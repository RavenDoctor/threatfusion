package com.adamshaddick.threatfusion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "threat_lookup_history")
@Getter
@Setter
@NoArgsConstructor
public class ThreatLookupHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;

    private Integer threatScore;

    private String riskLevel;

    private LocalDateTime searchedAt;
}