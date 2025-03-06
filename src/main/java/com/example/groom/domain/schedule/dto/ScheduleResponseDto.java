package com.example.groom.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
