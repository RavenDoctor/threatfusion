package com.adamshaddick.threatfusion.service;

import com.adamshaddick.threatfusion.exception.InvalidIpAddressException;
import com.adamshaddick.threatfusion.integration.geoip.GeoIpClient;
import com.adamshaddick.threatfusion.repository.ThreatLookupHistoryRepository;
import org.junit.jupiter.api.Test;
import com.adamshaddick.threatfusion.dto.ThreatLookupResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ThreatLookupServiceTest {

    @Test
    void shouldThrowExceptionForInvalidIp() {

        ThreatLookupHistoryRepository repository =
                mock(ThreatLookupHistoryRepository.class);

        GeoIpClient geoIpClient =
                mock(GeoIpClient.class);

        ThreatLookupService service =
                new ThreatLookupService(
                        repository,
                        geoIpClient
                );

        assertThrows(
                InvalidIpAddressException.class,
                () -> service.lookupIp("banana")
        );
    }
    @Test
    void shouldReturnThreatLookupResponseForValidIp() {

        ThreatLookupHistoryRepository repository =
                mock(ThreatLookupHistoryRepository.class);

        GeoIpClient geoIpClient =
                mock(GeoIpClient.class);

        when(geoIpClient.lookupCountry("8.8.8.8"))
                .thenReturn("United States");

        ThreatLookupService service =
                new ThreatLookupService(
                        repository,
                        geoIpClient
                );

        ThreatLookupResponse response =
                service.lookupIp("8.8.8.8");

        assertEquals(
                "United States",
                response.getCountry()
        );
    }
}