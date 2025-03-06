package com.example.groom.domain.schedule.teamSchedule.dto;

import com.example.groom.domain.auth.userInfo.dto.UserScheduleDto;
import com.example.groom.entity.domain.schedule.MeetingLocation;
import com.example.groom.entity.domain.schedule.TeamSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TeamScheduleDetailDto {

    private Long id;

    private String title;

    private String startTime;

    private String endTime;

    private MeetingLocation meetingLocation;

    private List<UserScheduleDto> participants;

    public static TeamScheduleDetailDto of(TeamSchedule teamSchedule) {
        List<UserScheduleDto> participants = teamSchedule.getParticipants().stream().map(participant -> UserScheduleDto.of(participant.getParticipant())).toList();

        return TeamScheduleDetailDto.builder()
                .id(teamSchedule.getId())
                .title(teamSchedule.getTitle())
                .startTime(teamSchedule.getStartTime().toString())
                .endTime(teamSchedule.getEndTime().toString())
                .meetingLocation(teamSchedule.getMeetingLocation())
                .participants(participants)
                .build();
    }
}
