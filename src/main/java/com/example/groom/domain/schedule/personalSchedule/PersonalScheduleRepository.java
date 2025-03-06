package com.example.groom.domain.schedule.personalSchedule;

import com.example.groom.entity.domain.schedule.PersonalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, Long> {
}
