package com.adamshaddick.threatfusion.integration.geoip;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GeoIpClient {

    private final WebClient webClient =
            WebClient.builder()
                    .baseUrl("http://ip-api.com")
                    .build();

    public String lookupCountry(String ipAddress) {

        GeoIpResponse response =
                webClient.get()
                        .uri("/json/" + ipAddress)
                        .retrieve()
                        .bodyToMono(GeoIpResponse.class)
                        .block();

        if (response == null) {
            return "Unknown";
        }

        return response.getCountry();
    }
}