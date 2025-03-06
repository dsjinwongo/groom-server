package com.example.groom.domain.schedule.teamSchedule;

import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleListDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleListResponseDto;
import com.example.groom.domain.schedule.teamSchedule.dto.TeamScheduleSearchCondition;
import com.example.groom.entity.domain.schedule.TeamSchedule;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.groom.entity.domain.schedule.QTeamSchedule.teamSchedule;


public class TeamScheduleRepositoryCustomImpl implements TeamScheduleRepositoryCustom {

    private final JPAQueryFactory query;

    @Autowired
    private EntityManager em;

    public TeamScheduleRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public TeamScheduleListResponseDto searchByCondition(Pageable pageable, TeamScheduleSearchCondition teamScheduleSearchCondition) {
//        List<TeamScheduleListDto> content = query
//                .from(teamScheduleUser)
//                .innerJoin(teamScheduleUser.teamSchedule, teamSchedule)
//                .innerJoin(teamScheduleUser.participant, userInfo)
//                .groupBy(teamSchedule.id)
//                .where(eqUserId(teamScheduleSearchCondition.getUserId()),
//                        eqRoomId(teamScheduleSearchCondition.getRoomId()),
//                        betweenScheduleTime(teamScheduleSearchCondition.getStartTime(), teamScheduleSearchCondition.getEndTime()))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .transform(
//                        groupBy(teamSchedule.id).list(
//                                Projections.fields(
//                                        TeamScheduleListDto.class,
//                                        teamSchedule.id,
//                                        teamSchedule.title,
//                                        teamSchedule.startTime,
//                                        teamSchedule.meetingLocation,
//                                        list(userInfo.kakao.kakaoAccount.profile.profileImageUrl).as("profiles")
//                                )
//                        )
//                );

        List<TeamSchedule> content = query
                .selectFrom(teamSchedule)
                .where(eqRoomId(teamScheduleSearchCondition.getRoomId()),
                        betweenScheduleTime(teamScheduleSearchCondition.getStartTime(), teamScheduleSearchCondition.getEndTime()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean isLast = getIsLast(content, pageable);

        List<TeamScheduleListDto> teamScheduleListDto = content.stream()
                .map(TeamScheduleListDto::of)
                .toList();

        return TeamScheduleListResponseDto.of(teamScheduleListDto, pageable.getPageNumber(), isLast);
    }

    private <T> boolean getIsLast(List<T> content, Pageable pageable) {
        boolean isLast = true;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            isLast = false;
        }
        return isLast;
    }

    private BooleanExpression betweenScheduleTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            return null;
        }
        if (endTime == null) {
            return null;
        }

        return teamSchedule.startTime.between(startTime, endTime)
                .or(teamSchedule.endTime.between(startTime, endTime));
    }

    private BooleanExpression eqRoomId(Long roomId) {
        if (roomId == null) {
            return null;
        }
        return teamSchedule.room.id.eq(roomId);
    }
}
