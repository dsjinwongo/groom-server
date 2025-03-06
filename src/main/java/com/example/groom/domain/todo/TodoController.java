package com.example.groom.domain.todo;

import com.example.groom.domain.todo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public TodoListResponseDto getTodoList(TodoSearchCondition todoSearchCondition, Pageable pageable) {
        return todoService.searchByCondition(todoSearchCondition, pageable);
    }

    @GetMapping("/{id}")
    public TodoDetailDto getTodoDetail(@PathVariable Long id) {
        return todoService.getTodoDetail(id);
    }

    @PostMapping
    public TodoDetailDto createTodo(@RequestBody TodoDto todoDto) {
        return todoService.createTodo(todoDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return id;
    }

    @PatchMapping
    public TodoDetailDto updateTodo(@RequestBody TodoUpdateDto todoUpdateDto) {
        return todoService.updateTodo(todoUpdateDto);
    }

    @PatchMapping("/file")
    public TodoDetailDto uploadFile(@RequestBody TodoFileDto todoFileDto) {
        return todoService.updateFile(todoFileDto);
    }

    @PatchMapping("/roomSlot")
    public TodoDetailDto updateRoomSlot(@RequestBody RoomSlotDto roomSlotDto) {
        return todoService.updateTodo(roomSlotDto);
    }
}
