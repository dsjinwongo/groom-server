package com.example.groom.domain.room;

import com.example.groom.domain.room.dto.RoomListResponseDto;
import com.example.groom.domain.room.dto.RoomSearchCondition;
import com.example.groom.entity.domain.room.Room;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoomRepositoryCustom {

    Optional<Room> findByCode(String code);

    RoomListResponseDto searchByCondition(Pageable pageable, RoomSearchCondition condition);
}
