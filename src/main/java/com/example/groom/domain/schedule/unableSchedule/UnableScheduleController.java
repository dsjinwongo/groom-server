package com.example.groom.domain.schedule.unableSchedule;

import com.example.groom.domain.schedule.unableSchedule.dto.UnableScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room/unableSchedule")
public class UnableScheduleController {

    private final UnableScheduleService unableScheduleService;

    @PostMapping
    public UnableScheduleDto postUnableSchedule(@RequestBody UnableScheduleDto unableScheduleDto) {
        unableScheduleService.createUnableSchedule(unableScheduleDto);
        return unableScheduleDto;
    }

    @DeleteMapping("/{id}")
    public Long deleteUnableSchedule(@PathVariable Long id) {
        unableScheduleService.deleteUnableSchedule(id);
        return id;
    }
}
