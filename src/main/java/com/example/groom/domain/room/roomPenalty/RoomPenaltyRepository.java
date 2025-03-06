package com.example.groom.domain.room.roomPenalty;


import com.example.groom.entity.domain.room.RoomPenalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPenaltyRepository extends JpaRepository<RoomPenalty, Long>, RoomPenaltyRepositoryCustom {
}
