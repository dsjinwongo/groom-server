package com.example.groom.domain.room;

import com.example.groom.domain.room.dto.RoomDto;
import com.example.groom.domain.room.dto.RoomListResponseDto;
import com.example.groom.domain.room.dto.RoomSearchCondition;
import com.example.groom.domain.room.roomParticipants.RoomParticipantsRepository;
import com.example.groom.entity.domain.room.Room;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.groom.entity.domain.room.QRoom.room;
import static com.example.groom.entity.domain.room.QRoomParticipants.roomParticipants;


public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory query;
    @Autowired
    private EntityManager em;
    @Autowired
    private RoomParticipantsRepository roomParticipantsRepository;

    public RoomRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Room> findByCode(String code) {
        return Optional.ofNullable(query.selectFrom(room)
                .where(room.roomInviteCode.code.eq(code))
                .fetchOne());
    }

    @Override
    public RoomListResponseDto searchByCondition(Pageable pageable, RoomSearchCondition condition) {
        List<RoomDto> content = query
                .select(Projections.constructor(RoomDto.class,
                        room.id,
                        room.name,
                        room.description,
                        room.mainImageUrl,
                        room.maxPeople,
                        room.roomParticipants.size(),
                        room.createdAt,
                        room.updatedAt
                ))
                .from(room)
                .innerJoin(room.roomParticipants, roomParticipants)
                .groupBy(room.id)
                .where(containsName(condition.getName()),
                        betweenDate(condition.getDateGoe(), condition.getDateLoe()),
                        eqOwnerId(condition.getOwnerId()),
                        containsDescription(condition.getDescription()),
                        eqParticipantId(condition.getParticipantId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean isLast = getIsLast(content, pageable);

        return RoomListResponseDto.of(content, pageable.getPageSize(), isLast);
    }

    private BooleanExpression eqParticipantId(Long participantId) {
        return participantId != null ? roomParticipants.roomParticipant.id.eq(participantId) : null;
    }

    private BooleanExpression eqOwnerId(Long ownerId) {
        if (ownerId == null) {
            return null;
        }
        return room.owner.id.eq(ownerId);
    }

    private BooleanExpression containsName(String name) {
        if (name == null) {
            return null;
        }
        return room.name.contains(name);
    }

    private BooleanExpression betweenDate(LocalDateTime dateGoe, LocalDateTime dateLoe) {
        if (dateGoe == null || dateLoe == null) {
            return null;
        }
        return room.createdAt.between(dateGoe, dateLoe);
    }

    private BooleanExpression containsDescription(String description) {
        if (description == null) {
            return null;
        }
        return room.description.contains(description);
    }

    private <T> boolean getIsLast(List<T> content, Pageable pageable) {
        boolean isLast = true;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            isLast = false;
        }
        return isLast;
    }
}
