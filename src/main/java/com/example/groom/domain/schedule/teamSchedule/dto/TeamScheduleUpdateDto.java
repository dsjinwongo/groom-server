package com.example.groom.domain.schedule.teamSchedule.dto;

import com.example.groom.entity.domain.schedule.MeetingLocation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TeamScheduleUpdateDto {
    private Long id;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private MeetingLocation meetingLocation;

    private List<Long> participants;
}
