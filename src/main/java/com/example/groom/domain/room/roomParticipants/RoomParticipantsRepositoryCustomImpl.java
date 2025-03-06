package com.example.groom.domain.room.roomParticipants;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.room.QRoomParticipants.roomParticipants;


public class RoomParticipantsRepositoryCustomImpl implements RoomParticipantsRepositoryCustom {

    private final JPAQueryFactory query;

    @Autowired
    private EntityManager em;

    public RoomParticipantsRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Long> getParticipantsIds(Long roomId) {
        return query.select(roomParticipants.roomParticipant.id)
                .from(roomParticipants)
                .where(roomParticipants.room.id.eq(roomId))
                .fetch();
    }
}
