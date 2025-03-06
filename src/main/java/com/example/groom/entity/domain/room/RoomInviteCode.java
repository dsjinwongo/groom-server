package com.example.groom.entity.domain.room;


import com.example.groom.entity.domain.InviteCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomInviteCode extends InviteCode {
    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Room room;

    protected RoomInviteCode(Long roomId) {
        super();
        this.room = Room.of(roomId);
    }

    static public RoomInviteCode of(Long roomId) {
        return new RoomInviteCode(roomId);
    }
//TODO
//    @JoinColumn
//    @OneToOne(fetch = FetchType.LAZY)
//    private Groom groom;

}
