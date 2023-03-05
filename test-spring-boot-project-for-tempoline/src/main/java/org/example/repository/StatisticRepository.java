package org.example.repository;

import org.example.entity.StatisticEvent;
import org.example.enums.StatisticEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEvent, Long> {
    List<StatisticEvent> findAllByStatisticEventTypeOrderByCreatedAtDesc(StatisticEventType statisticEventType);
}
