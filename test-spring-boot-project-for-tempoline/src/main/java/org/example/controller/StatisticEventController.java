package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.StatisticEventDto;
import org.example.service.StatisticEventService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/statistic-management/statistics")
public class StatisticEventController {
    private StatisticEventService statisticEventService;

    @PostMapping
    public void creatEvent(@RequestBody @Valid StatisticEventDto statisticEventDto) {
        statisticEventService.createEvent(statisticEventDto);
    }

    @GetMapping()
    @ResponseBody
    public List<StatisticEventDto> getEvents() {
        return statisticEventService.getEvents();
    }

    @GetMapping()
    @RequestMapping("type/{type}/page/{page}/size/{size}")
    public Page<StatisticEventDto> getEventsByType(@PathVariable String type,
                                                          @PathVariable Integer page,
                                                          @PathVariable Integer size) {

        return statisticEventService.getEventsByType(type, page, size);
    }

}
