package com.example.groom.domain.schedule.teamScheduleUser;

import com.example.groom.domain.schedule.dto.ScheduleDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleSearchCondition;
import com.example.groom.entity.enums.RequestStatus;

import java.util.List;

public interface TeamScheduleUserRepositoryCustom {
    void deleteByParticipantIdAndTeamScheduleId(Long participantId, Long teamScheduleId);

    List<ScheduleDto> searchByCondition(TeamScheduleSearchCondition teamScheduleSearchCondition);

    // 특정 팀스케줄의 참가여부 수정
    void updateParticipation(Long teamScheduleId, Long userId, RequestStatus status);

    // 특정 팀스케줄의 참가자 목록 가져오기
    List<Long> getParticipants(Long teamScheduleId);
}
