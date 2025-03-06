package com.example.groom.entity.domain.schedule;

import com.example.groom.domain.schedule.dto.ScheduleDto;
import com.example.groom.entity.common.OwnEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Schedule_Type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends OwnEntity {

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    protected Schedule(ScheduleDto scheduleDto) {
        this.startTime = scheduleDto.getStartTime();
        this.endTime = scheduleDto.getEndTime();
    }

    public Schedule(Long id) {
        super(id);
    }

    static public Schedule of(ScheduleDto scheduleDto) {
        return new Schedule(scheduleDto);
    }
}

