package com.example.groom.domain.todo.dto;

import com.example.groom.entity.enums.RoomSlotSample;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoListDto {

    private Long id;

    private String title;

    private String content;

    private String nickname;

    private String profileImage;

    private RoomSlotSample roomSlot;

    private Long todoOwnerId;
}
