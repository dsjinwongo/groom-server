package com.example.groom.entity.domain.room;


import com.example.groom.domain.room.roomPenalty.dto.RoomPenaltyPostDto;
import com.example.groom.entity.common.BaseEntity;
import com.example.groom.entity.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomPenalty extends BaseEntity {
    @Column
    private Long maxAmount;

    @JoinColumn
    @ManyToOne
    private Product product;

    @JoinColumn
    @ManyToOne
    private Room room;

    private RoomPenalty(RoomPenaltyPostDto roomPenaltyPostDto){
        this.maxAmount = roomPenaltyPostDto.getMaxAmount();
        this.room = Room.of(roomPenaltyPostDto.getRoomId());
        this.product = Product.of(roomPenaltyPostDto.getGifticonId());
    }

    static public RoomPenalty of(RoomPenaltyPostDto roomPenaltyPostDto){
        return new RoomPenalty(roomPenaltyPostDto);
    }
}
