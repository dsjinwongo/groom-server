package com.example.groom.domain.room.roomParticipants;

import java.util.List;

public interface RoomParticipantsRepositoryCustom {

    List<Long> getParticipantsIds(Long roomId);
}
