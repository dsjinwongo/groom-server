package com.example.groom.domain.schedule;

import com.example.groom.domain.schedule.dto.ScheduleSearchCondition;
import com.example.groom.entity.domain.schedule.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    // 목록
    Page<Schedule> searchByCondition(Pageable pageable, ScheduleSearchCondition scheduleSearchCondition);
}
