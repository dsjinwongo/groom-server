package com.example.groom.domain.room.roomParticipants;

import com.example.groom.domain.room.roomParticipants.dto.RoomParticipantsDto;
import com.example.groom.entity.domain.room.RoomParticipants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomParticipantsService {
    private final RoomParticipantsRepository roomParticipantsRepository;

    public List<Long> getParticipantsIds(Long roomId) {
        return this.roomParticipantsRepository.getParticipantsIds(roomId);
    }

    @Transactional
    public void delete(RoomParticipantsDto roomParticipantsDto) {
        this.roomParticipantsRepository.deleteByRoomIdAndRoomParticipantId(roomParticipantsDto.getRoomId(), roomParticipantsDto.getUserId());
    }

    public void save(RoomParticipants roomParticipants) {
        this.roomParticipantsRepository.save(roomParticipants);
    }

    public void saveAll(Long roomId, List<Long> roomParticipants) {
        List<RoomParticipants> participants = roomParticipants.stream().map(rp -> RoomParticipants.of(roomId, rp)).toList();
        this.roomParticipantsRepository.saveAll(participants);
    }


}
