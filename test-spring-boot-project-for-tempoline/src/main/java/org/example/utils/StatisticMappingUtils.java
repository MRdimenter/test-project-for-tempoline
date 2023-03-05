package org.example.utils;

import lombok.AllArgsConstructor;
import org.example.dto.StatisticEventDto;
import org.example.entity.StatisticEvent;
import org.example.enums.StatisticEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class StatisticMappingUtils {

    public StatisticEvent mapToStatisticEventEntity(StatisticEventDto statisticEventDto) {
        return StatisticEvent.builder()
                .createdAt(Calendar.getInstance())
                .message(statisticEventDto.getMessage())
                .statisticEventType(StatisticEventType.valueOf(statisticEventDto.getType()))
                .build();
    }

    public List<StatisticEventDto> mapListToStatisticEventDto(List<StatisticEvent> statisticEventList) {
        List<StatisticEventDto> statisticEventDtoList = new ArrayList<>();
        statisticEventList.forEach(statisticEvent -> {
            statisticEventDtoList.add(StatisticEventDto.builder()
                    .id(statisticEvent.getId())
                    .message(statisticEvent.getMessage())
                    .type(statisticEvent.getStatisticEventType().name())
                    .createdAt(statisticEvent.getCreatedAt())
                    .year(statisticEvent.getCreatedAt().get(Calendar.YEAR))
                    .month(statisticEvent.getCreatedAt().get(Calendar.MONTH ) + 1)
                    .day(statisticEvent.getCreatedAt().get(Calendar.DATE))
                    .build());
        });

        return statisticEventDtoList;
    }


}
