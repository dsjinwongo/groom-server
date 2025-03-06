package com.example.groom.domain.schedule.personalSchedule;

import com.example.groom.domain.schedule.personalSchedule.dto.PersonalScheduleDto;
import com.example.groom.entity.domain.schedule.PersonalSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalScheduleService {

    private final PersonalScheduleRepository personalScheduleRepository;

    public void createPersonalSchedule(PersonalScheduleDto personalScheduleDto) {
        PersonalSchedule personalSchedule = PersonalSchedule.of(personalScheduleDto);
        personalScheduleRepository.save(personalSchedule);
    }

    public void deletePersonalSchedule(Long id) {
        personalScheduleRepository.deleteById(id);
    }
}
