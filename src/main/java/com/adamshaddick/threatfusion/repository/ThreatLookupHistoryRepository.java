package com.adamshaddick.threatfusion.repository;

import com.adamshaddick.threatfusion.entity.ThreatLookupHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreatLookupHistoryRepository
        extends JpaRepository<ThreatLookupHistory, Long> {

    List<ThreatLookupHistory> findAllByOrderBySearchedAtDesc();
}