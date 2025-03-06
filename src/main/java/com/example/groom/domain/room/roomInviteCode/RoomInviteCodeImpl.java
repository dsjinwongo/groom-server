package com.example.groom.domain.room.roomInviteCode;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class RoomInviteCodeImpl implements RoomInviteCodeCustom {

    private final JPAQueryFactory query;
    @Autowired
    private EntityManager em;

    public RoomInviteCodeImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
