package com.example.groom.domain.gifticon.dto;


import com.querydsl.core.BooleanBuilder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.example.groom.entity.domain.gifticon.QGifticon.gifticon;

@Data
public class GifticonSearchCondition {
    private Long id;
    private String message;
    private Long productId;
    private Long receiverId;
    private Long ownerId;
    private Long organizationId;
    private LocalDateTime createdAtLoe;
    private LocalDateTime createdAtGoe;

    private BooleanBuilder idEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(id != null)builder.and(gifticon.id.eq(id));
        return builder;
    }

    private BooleanBuilder messageContains(){
        BooleanBuilder builder = new BooleanBuilder();
        if(message != null && !message.isBlank())builder.and(gifticon.message.contains(message));
        return builder;
    }

    private BooleanBuilder productIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(productId != null)builder.and(gifticon.product.id.eq(productId));
        return builder;
    }

    private BooleanBuilder organizationIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(organizationId != null)builder.and(gifticon.product.organization.id.eq(organizationId));
        return builder;
    }

    private BooleanBuilder receiverIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(receiverId != null)builder.and(gifticon.receiver.id.eq(receiverId));
        return builder;
    }

    private BooleanBuilder ownerIdEq(){
        BooleanBuilder builder = new BooleanBuilder();
        if(ownerId != null)builder.and(gifticon.owner.id.eq(ownerId));
        return builder;
    }

    private BooleanBuilder createdAtBetween(){
        BooleanBuilder builder = new BooleanBuilder();
        if(createdAtGoe != null)builder.and(gifticon.createdAt.goe(createdAtGoe));
        if(createdAtLoe != null)builder.and(gifticon.createdAt.loe(createdAtLoe));
        return builder;
    }

    public BooleanBuilder getAllCondition(){
        BooleanBuilder builder = new BooleanBuilder();
        return builder.and(idEq()).and(messageContains()).and(productIdEq()).and(organizationIdEq())
                .and(receiverIdEq()).and(ownerIdEq()).and(createdAtBetween());
    }
}
