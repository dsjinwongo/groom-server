package com.example.groom.domain.schedule.teamScheduleUser;

import com.example.groom.entity.domain.schedule.TeamScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamScheduleUserRepository extends JpaRepository<TeamScheduleUser, Long>, TeamScheduleUserRepositoryCustom {
//    void deleteByParticipantIdAndTeamScheduleId(Long participantId, Long teamScheduleId);
}