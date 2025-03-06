package com.example.groom.domain.schedule.teamScheduleUser;

import com.example.groom.domain.schedule.dto.ScheduleDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleSearchCondition;
import com.example.groom.domain.schedule.teamScheduleUser.dto.TeamScheduleUserDto;
import com.example.groom.entity.domain.schedule.TeamScheduleUser;
import com.example.groom.entity.enums.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamScheduleUserService {

    private final TeamScheduleUserRepository teamScheduleUserRepository;

    public void createTeamScheduleUser(TeamScheduleUserDto teamScheduleUserDto) {
        teamScheduleUserRepository.save(TeamScheduleUser.of(teamScheduleUserDto));
    }

    @Transactional
    public void deleteTeamScheduleUser(Long participantId, Long teamScheduleId) {
        teamScheduleUserRepository.deleteByParticipantIdAndTeamScheduleId(participantId, teamScheduleId);
    }

    public List<ScheduleDto> searchByCondition(TeamScheduleSearchCondition teamScheduleSearchCondition) {
        return teamScheduleUserRepository.searchByCondition(teamScheduleSearchCondition);
    }

    public List<Long> getParticipants(Long teamScheduleId) {
        return teamScheduleUserRepository.getParticipants(teamScheduleId);
    }

    public void updateParticipation(Long teamScheduleId, Long userId, RequestStatus status) {
        teamScheduleUserRepository.updateParticipation(teamScheduleId, userId, status);
    }

}
