package com.example.groom.entity.domain.room;


import com.example.groom.entity.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class RoomParticipantsPenalty extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private RoomParticipants roomParticipants;
}
