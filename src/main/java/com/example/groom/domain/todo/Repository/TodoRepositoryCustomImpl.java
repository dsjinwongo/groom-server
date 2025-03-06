package com.example.groom.domain.todo.Repository;

import com.example.groom.domain.todo.dto.TodoListDto;
import com.example.groom.domain.todo.dto.TodoListResponseDto;
import com.example.groom.domain.todo.dto.TodoSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.todo.QTodo.todo;

public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {

    private final JPAQueryFactory query;
    @Autowired
    private EntityManager em;

    public TodoRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public TodoListResponseDto searchByCondition(TodoSearchCondition todoSearchCondition, Pageable pageable) {

        List<TodoListDto> content = query
                .select(Projections.constructor(TodoListDto.class,
                        todo.id,
                        todo.title,
                        todo.content,
                        todo.todoOwner.kakao.kakaoAccount.profile.nickname,
                        todo.todoOwner.kakao.kakaoAccount.profile.profileImageUrl,
                        todo.roomSlot,
                        todo.todoOwner.id))
                .from(todo)
                .where(eqRoomId(todoSearchCondition.getRoomId()),
                        eqUserId(todoSearchCondition.getUserId()))
//                        eqTodoBoxId(todoSearchCondition.getTodoBoxId()),
//                        eqRoomSlotId(todoSearchCondition.getRoomSlotId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean isLast = getHasNext(content, pageable);

        return TodoListResponseDto.of(content, pageable.getPageNumber(), isLast);
    }

    private BooleanExpression eqRoomId(Long roomId) {
        return roomId != null ? todo.room.id.eq(roomId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? todo.todoOwner.id.eq(userId) : null;
    }

//    private BooleanExpression eqRoomSlotId(Long roomSlotId) {
//        return roomSlotId != null ? todo.roomSlot.id.eq(roomSlotId) : null;
//    }
//
//    private BooleanExpression eqTodoBoxId(Long todoBoxId) {
//        return todoBoxId != null ? todo.todoBox.id.eq(todoBoxId) : null;
//    }

    private <T> boolean getHasNext(List<T> content, Pageable pageable) {
        boolean isLast = true;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            isLast = false;
        }
        return isLast;
    }
}
