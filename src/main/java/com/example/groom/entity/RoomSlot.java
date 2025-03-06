package com.example.groom.entity;

import com.example.groom.entity.common.CoopEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomSlot extends CoopEntity {

    @Column
    private String colorHex;

    @Column
    private String name;

    @Column
    private Long order;

    protected RoomSlot(Long roomSlotId) {
        super(roomSlotId);
    }

    public static RoomSlot of(Long roomSlotId) {
        return new RoomSlot(roomSlotId);
    }

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Long room_id;
}
