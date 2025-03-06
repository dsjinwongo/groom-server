package com.example.groom.domain.schedule.unableSchedule.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UnableScheduleDto {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long roomId;
}
