package com.example.groom.domain.room.roomParticipants;

import com.example.groom.entity.domain.room.RoomParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantsRepository extends JpaRepository<RoomParticipants, Long>, RoomParticipantsRepositoryCustom {
    void deleteByRoomIdAndRoomParticipantId(Long roomId, Long userId);
}
