package com.example.groom.entity.domain.room;


import com.example.groom.entity.common.BaseEntity;
import com.example.groom.entity.domain.auth.UserInfo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomParticipants extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserInfo roomParticipant;

    private RoomParticipants(Long id) {
        super(id);
    }

    public static RoomParticipants of(Long id) {
        return new RoomParticipants(id);
    }

    public static RoomParticipants of(Long roomId, Long userId) {
        return RoomParticipants.builder().room(Room.of(roomId)).roomParticipant(UserInfo.of(userId)).build();
    }
}
