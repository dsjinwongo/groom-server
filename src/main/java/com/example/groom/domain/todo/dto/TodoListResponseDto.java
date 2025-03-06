package com.example.groom.domain.todo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TodoListResponseDto {

    private List<TodoListDto> todoList;

    private int page;

    private boolean last;

    public static TodoListResponseDto of(List<TodoListDto> todoList, int pageNumber, boolean isLast) {
        return TodoListResponseDto.builder()
                .todoList(todoList)
                .page(pageNumber)
                .last(isLast)
                .build();
    }
}
