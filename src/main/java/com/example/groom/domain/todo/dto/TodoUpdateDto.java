package com.example.groom.domain.todo.dto;

import com.example.groom.entity.enums.RoomSlotSample;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoUpdateDto {

    private Long id;

    private String title;

    private String content;

    private String fileUrl;

    private String fileName;

    private RoomSlotSample roomSlot;

    private Long todoOwnerId;
}