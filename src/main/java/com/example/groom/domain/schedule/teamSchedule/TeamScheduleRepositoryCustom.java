package com.example.groom.domain.schedule.teamSchedule;

import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleListResponseDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleSearchCondition;
import org.springframework.data.domain.Pageable;

public interface TeamScheduleRepositoryCustom {

    // 팀의 스케줄 목록 가져오기
    TeamScheduleListResponseDto searchByCondition(Pageable pageable, TeamScheduleSearchCondition teamScheduleSearchCondition);
}
