package com.example.groom.domain.room.roomInviteCode;

import com.example.groom.entity.domain.room.RoomInviteCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomInviteCodeRepository extends JpaRepository<RoomInviteCode, Long>, RoomInviteCodeCustom {
    Optional<RoomInviteCode> findByRoomId(Long roomId);
}
