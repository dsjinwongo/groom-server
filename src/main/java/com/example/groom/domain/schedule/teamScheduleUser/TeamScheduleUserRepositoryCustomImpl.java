package com.example.groom.domain.schedule.teamScheduleUser;

import com.example.groom.domain.schedule.dto.ScheduleDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleSearchCondition;
import com.example.groom.entity.enums.RequestStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.groom.entity.domain.schedule.QTeamScheduleUser.teamScheduleUser;

public class TeamScheduleUserRepositoryCustomImpl implements TeamScheduleUserRepositoryCustom {

    private final JPAQueryFactory query;

    @Autowired
    private EntityManager em;

    public TeamScheduleUserRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void deleteByParticipantIdAndTeamScheduleId(Long participantId, Long teamScheduleId) {
        query.delete(teamScheduleUser)
                .where(teamScheduleUser.participant.id.eq(participantId))
                .where(teamScheduleUser.teamSchedule.id.eq(teamScheduleId))
                .execute();
    }

    @Override
    public List<ScheduleDto> searchByCondition(TeamScheduleSearchCondition teamScheduleSearchCondition) {
        return query.select(Projections.constructor(ScheduleDto.class,
                        teamScheduleUser.teamSchedule.startTime,
                        teamScheduleUser.teamSchedule.endTime
                ))
                .from(teamScheduleUser)
                .where(eqUserId(teamScheduleSearchCondition.getUserId()),
                        eqRoomId(teamScheduleSearchCondition.getRoomId()),
                        betweenScheduleTime(teamScheduleSearchCondition.getStartTime(), teamScheduleSearchCondition.getEndTime()))
                .distinct()
                .fetch();
    }

    @Override
    public void updateParticipation(Long teamScheduleId, Long userId, RequestStatus status) {
        query.update(teamScheduleUser)
                .set(teamScheduleUser.status, status)
                .where(teamScheduleUser.id.eq(teamScheduleId), teamScheduleUser.participant.id.eq(userId))
                .execute();
    }

    @Override
    public List<Long> getParticipants(Long teamScheduleId) {
        return query.select(teamScheduleUser.participant.id)
                .from(teamScheduleUser)
                .where(teamScheduleUser.teamSchedule.id.eq(teamScheduleId))
                .fetch();
    }

    private BooleanExpression betweenScheduleTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            return null;
        }
        if (endTime == null) {
            return null;
        }

        return teamScheduleUser.teamSchedule.startTime.between(startTime, endTime)
                .or(teamScheduleUser.teamSchedule.endTime.between(startTime, endTime));
    }

    private BooleanExpression eqRoomId(Long roomId) {
        if (roomId == null) {
            return null;
        }
        return teamScheduleUser.teamSchedule.room.id.eq(roomId);
    }

    private BooleanExpression eqUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        return teamScheduleUser.participant.id.eq(userId);
    }
}
