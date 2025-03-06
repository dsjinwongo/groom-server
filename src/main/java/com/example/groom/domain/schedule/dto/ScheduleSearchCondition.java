package com.example.groom.domain.schedule.dto;

import com.example.groom.entity.domain.auth.UserInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleSearchCondition {
    private UserInfo owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
