package com.example.groom.domain.schedule.unableSchedule;

import com.example.groom.domain.schedule.dto.ScheduleResponseDto;
import com.example.groom.entity.domain.schedule.UnableSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnableScheduleRepository extends JpaRepository<UnableSchedule, Long> {
    List<ScheduleResponseDto> findByRoomId(Long roomId);
}
