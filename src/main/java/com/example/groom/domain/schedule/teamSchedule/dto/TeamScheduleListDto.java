package com.example.groom.domain.schedule.teamSchedule.dto;

import com.example.groom.entity.domain.schedule.MeetingLocation;
import com.example.groom.entity.domain.schedule.TeamSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamScheduleListDto {

    private Long id;

    private String title;

    private LocalDateTime startTime;

    private MeetingLocation meetingLocation;

    private List<String> profiles = new ArrayList<>();

    public static TeamScheduleListDto of(TeamSchedule teamSchedule) {
        List<String> profiles = teamSchedule.getParticipants().stream()
                .map(teamScheduleUser -> teamScheduleUser.getParticipant().getKakao().getKakaoAccount().getProfile().getProfileImageUrl())
                .toList();

        return TeamScheduleListDto.builder()
                .id(teamSchedule.getId())
                .title(teamSchedule.getTitle())
                .startTime(teamSchedule.getStartTime())
                .meetingLocation(teamSchedule.getMeetingLocation())
                .profiles(profiles)
                .build();
    }
}
