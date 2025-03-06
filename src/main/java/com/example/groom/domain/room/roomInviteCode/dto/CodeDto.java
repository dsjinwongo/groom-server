package com.example.groom.domain.room.roomInviteCode.dto;

import com.example.groom.entity.domain.room.RoomInviteCode;
import lombok.Data;

@Data
public class CodeDto {
    private String code;

    protected CodeDto(RoomInviteCode code){
        this.code = code.getCode();
    }

    public static CodeDto of(RoomInviteCode code) {
        return new CodeDto(code);
    }
}
