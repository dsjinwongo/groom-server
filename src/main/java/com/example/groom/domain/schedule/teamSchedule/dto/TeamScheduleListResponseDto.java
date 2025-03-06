package com.example.groom.domain.schedule.teamSchedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TeamScheduleListResponseDto {

    private List<TeamScheduleListDto> teamScheduleList;

    private int page;

    private boolean last;

    public static TeamScheduleListResponseDto of(List<TeamScheduleListDto> teamScheduleList, int pageNumber, boolean isLast) {
        return TeamScheduleListResponseDto.builder()
                .teamScheduleList(teamScheduleList)
                .page(pageNumber)
                .last(isLast)
                .build();
    }

}
