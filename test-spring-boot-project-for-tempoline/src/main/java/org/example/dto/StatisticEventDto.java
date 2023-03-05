package org.example.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StatisticEventDto {
    private Long id;
    @Nonnull
    private String message;
    private String type;
    private Calendar createdAt;
    private Integer year;
    private Integer month;
    private Integer day;


}
