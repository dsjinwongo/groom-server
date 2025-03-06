package com.example.groom.domain.room.roomPenalty;


import com.example.groom.domain.room.roomPenalty.dto.RoomPenaltyPostDto;
import com.example.groom.entity.domain.room.RoomPenalty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomPenaltyService {
    private final RoomPenaltyRepository roompenaltyRepository;

    public RoomPenalty save(RoomPenaltyPostDto roomPenaltyPostDto){
        RoomPenalty roomPenalty = RoomPenalty.of(roomPenaltyPostDto);
        return this.roompenaltyRepository.save(roomPenalty);
    }
}
