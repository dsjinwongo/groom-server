package com.example.groom.domain.room.roomParticipants.dto;


import lombok.Data;

@Data
public class RoomParticipantsDto {

    private Long roomId;
    private Long userId;

    protected RoomParticipantsDto(Long roomId, Long userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public static RoomParticipantsDto of(Long roomId, Long userId) {
        return new RoomParticipantsDto(roomId, userId);
    }
}
