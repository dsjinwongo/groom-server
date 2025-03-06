package com.example.groom.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleDto {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public static ScheduleDto of(ScheduleResponseDto scheduleResponseDto) {
        return new ScheduleDto(scheduleResponseDto.getStartTime(), scheduleResponseDto.getEndTime());
    }
}
