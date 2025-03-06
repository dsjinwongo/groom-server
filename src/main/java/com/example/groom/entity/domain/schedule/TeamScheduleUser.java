package com.example.groom.entity.domain.schedule;

import com.example.groom.domain.schedule.teamScheduleUser.dto.TeamScheduleUserDto;
import com.example.groom.entity.common.BaseEntity;
import com.example.groom.entity.domain.auth.UserInfo;
import com.example.groom.entity.enums.RequestStatus;
import com.querydsl.core.annotations.QueryInit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"team_schedule_id", "participant_id"})})
public class TeamScheduleUser extends BaseEntity {

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @QueryInit("room")
    private TeamSchedule teamSchedule;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @QueryInit({"kakao.kakaoAccount.profile"})
    private UserInfo participant;

    @Column
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    protected TeamScheduleUser(TeamScheduleUserDto teamScheduleUserDto) {
        this.teamSchedule = TeamSchedule.of(teamScheduleUserDto.getTeamScheduleId());
        this.participant = UserInfo.of(teamScheduleUserDto.getUserId());
        this.status = teamScheduleUserDto.getStatus();
    }

    static public TeamScheduleUser of(TeamScheduleUserDto teamScheduleUserDto) {
        return new TeamScheduleUser(teamScheduleUserDto);
    }

}
