package com.example.groom.domain.schedule.teamSchedule;

import com.example.groom.entity.domain.schedule.TeamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Long>, TeamScheduleRepositoryCustom {
}
