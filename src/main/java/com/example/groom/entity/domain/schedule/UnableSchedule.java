package com.example.groom.entity.domain.schedule;

import com.example.groom.domain.schedule.unableSchedule.dto.UnableScheduleDto;
import com.example.groom.entity.common.OwnEntity;
import com.example.groom.entity.domain.room.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@DiscriminatorValue(value = "UNABLE_SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnableSchedule extends OwnEntity {

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    protected UnableSchedule(UnableScheduleDto unableScheduleDto) {
        this.startTime = unableScheduleDto.getStartTime();
        this.endTime = unableScheduleDto.getEndTime();
        this.room = Room.of(unableScheduleDto.getRoomId());
    }

    static public UnableSchedule of(UnableScheduleDto unableScheduleDto) {
        return new UnableSchedule(unableScheduleDto);
    }
}
