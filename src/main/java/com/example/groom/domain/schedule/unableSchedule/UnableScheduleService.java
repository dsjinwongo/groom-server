package com.example.groom.domain.schedule.unableSchedule;

import com.example.groom.domain.schedule.dto.ScheduleDto;
import com.example.groom.domain.schedule.dto.ScheduleResponseDto;
import com.example.groom.domain.schedule.unableSchedule.dto.UnableScheduleDto;
import com.example.groom.entity.domain.schedule.UnableSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnableScheduleService {

    private final UnableScheduleRepository unableScheduleRepository;

    public void createUnableSchedule(UnableScheduleDto unableScheduleDto) {
        UnableSchedule unableSchedule = UnableSchedule.of(unableScheduleDto);
        unableScheduleRepository.save(unableSchedule);
    }

    public List<ScheduleDto> searchUnableSchedule(Long roomId) {
        return unableScheduleRepository.findByRoomId(roomId).stream()
                .map(ScheduleDto::of)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponseDto> searchSortedUnableSchedule(Long roomId) {
        return unableScheduleRepository.findByRoomId(roomId).stream().sorted((o1, o2) -> {
            if (o1.getStartTime().isBefore(o2.getStartTime())) {
                return -1;
            } else if (o1.getStartTime().isAfter(o2.getStartTime())) {
                return 1;
            } else {
                if (o1.getEndTime().isBefore(o2.getEndTime())) {
                    return -1;
                } else if (o1.getEndTime().isAfter(o2.getEndTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
    }

    public void deleteUnableSchedule(Long id) {
        unableScheduleRepository.deleteById(id);
    }
}
