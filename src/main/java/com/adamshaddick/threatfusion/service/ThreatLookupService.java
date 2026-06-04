package com.adamshaddick.threatfusion.service;

import com.adamshaddick.threatfusion.dto.ThreatLookupResponse;
import com.adamshaddick.threatfusion.entity.ThreatLookupHistory;
import com.adamshaddick.threatfusion.exception.InvalidIpAddressException;
import com.adamshaddick.threatfusion.integration.geoip.GeoIpClient;
import com.adamshaddick.threatfusion.repository.ThreatLookupHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThreatLookupService {

    private final ThreatLookupHistoryRepository repository;
    private final GeoIpClient geoIpClient;

    public ThreatLookupService(
            ThreatLookupHistoryRepository repository,
            GeoIpClient geoIpClient) {

        this.repository = repository;
        this.geoIpClient = geoIpClient;
    }

    public ThreatLookupResponse lookupIp(String ipAddress) {

        if (!isValidIpv4(ipAddress)) {
            throw new InvalidIpAddressException(
                    "Invalid IPv4 address: " + ipAddress
            );
        }

        int threatScore = Math.abs(ipAddress.hashCode()) % 100;

        String riskLevel;

        if (threatScore >= 70) {
            riskLevel = "HIGH";
        } else if (threatScore >= 40) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "LOW";
        }

        String country =
                geoIpClient.lookupCountry(ipAddress);

        ThreatLookupHistory history = new ThreatLookupHistory();

        history.setIpAddress(ipAddress);
        history.setThreatScore(threatScore);
        history.setRiskLevel(riskLevel);
        history.setSearchedAt(LocalDateTime.now());

        repository.save(history);

        return new ThreatLookupResponse(
                ipAddress,
                threatScore,
                country,
                riskLevel
        );
    }

    public List<ThreatLookupHistory> getHistory() {
        return repository.findAllByOrderBySearchedAtDesc();
    }

    private boolean isValidIpv4(String ipAddress) {

        String ipv4Pattern =
                "^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}" +
                        "(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$";

        return ipAddress.matches(ipv4Pattern);
    }
}