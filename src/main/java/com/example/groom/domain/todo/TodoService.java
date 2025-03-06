package com.example.groom.domain.todo;

import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.domain.auth.userInfo.UserInfoRepository;
import com.example.groom.domain.todo.Repository.TodoRepository;
import com.example.groom.domain.todo.dto.*;
import com.example.groom.entity.domain.auth.UserInfo;
import com.example.groom.entity.domain.todo.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserInfoRepository userInfoRepository;

    public TodoDetailDto getTodoDetail(Long id) {
        return TodoDetailDto.of(this.todoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND)));
    }

    public TodoListResponseDto searchByCondition(TodoSearchCondition todoSearchCondition, Pageable pageable) {
        // 리스트는 널값 체크 안해도 됨
        return this.todoRepository.searchByCondition(todoSearchCondition, pageable);
    }

    public TodoDetailDto createTodo(TodoDto todoDto) {
        Todo todo = this.todoRepository.save(Todo.of(todoDto));

        UserInfo todoOwner = userInfoRepository.findById(todoDto.getTodoOwnerId()).get();

        return TodoDetailDto.of(todo, todoOwner);
    }

    public void deleteTodo(Long id) {

        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        } else {
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        }
    }

    @Transactional
    public TodoDetailDto updateFile(TodoFileDto todoFileDto) {
        Todo todo = this.todoRepository.findById(todoFileDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todo.of(todoFileDto);

        return TodoDetailDto.of(todo);
    }

    @Transactional
    public TodoDetailDto updateTodo(TodoUpdateDto todoUpdateDto) {
        Todo todo = this.todoRepository.findById(todoUpdateDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todo = todo.of(todoUpdateDto);
        UserInfo todoOwner = userInfoRepository.findById(todoUpdateDto.getTodoOwnerId()).get();

        // TODO: 2022-11-12 Todo todoOnwer 조회쿼리 없이 못하나..?

        return TodoDetailDto.of(todo, todoOwner);
    }

    @Transactional
    public TodoDetailDto updateTodo(RoomSlotDto roomSlotDto) {
        Todo todo = this.todoRepository.findById(roomSlotDto.getId()).orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

        todo = todo.of(roomSlotDto.getRoomSlot());

        return TodoDetailDto.of(todo);
    }
}
