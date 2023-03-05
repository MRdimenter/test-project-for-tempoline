package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.StatisticEventDto;
import org.example.enums.StatisticEventType;
import org.example.repository.StatisticRepository;
import org.example.utils.StatisticMappingUtils;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticEventService {
    private StatisticRepository statisticSpecialRepository;
    private StatisticMappingUtils statisticMappingUtils;

    public void createEvent(StatisticEventDto statisticEventDto) {
        statisticSpecialRepository.save(statisticMappingUtils.mapToStatisticEventEntity(statisticEventDto));
    }


    public List<StatisticEventDto> getEvents() {
        return statisticMappingUtils.mapListToStatisticEventDto(statisticSpecialRepository.findAll());
    }


    public Page<StatisticEventDto> getEventsByType(String type, Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        List<StatisticEventDto> statisticEventDtoList = statisticMappingUtils.mapListToStatisticEventDto(statisticSpecialRepository
                .findAllByStatisticEventTypeOrderByCreatedAtDesc(StatisticEventType.valueOf(type)));

        List<StatisticEventDto> statisticEventDtoSubList = statisticEventDtoList.subList((page - 1) * size, (page * size));

        return new PageImpl<>(statisticEventDtoSubList, paging, statisticEventDtoSubList.size());
    }
}
