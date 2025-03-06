package com.example.groom.domain.room.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomListResponseDto {

    private List<RoomDto> roomDtoList;

    private int page;

    private boolean last;

    protected RoomListResponseDto(List<RoomDto> roomDtoList, int page, boolean last) {
        this.roomDtoList = roomDtoList;
        this.page = page;
        this.last = last;
    }

    public static RoomListResponseDto of(List<RoomDto> roomDtoList, int page, boolean last) {
        return new RoomListResponseDto(roomDtoList, page, last);
    }
}
