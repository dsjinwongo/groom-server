package com.example.groom.domain.schedule.personalSchedule.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonalScheduleDto {

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public PersonalScheduleDto(String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
