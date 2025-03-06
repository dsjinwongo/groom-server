package com.example.groom.domain.room.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String name;
    private String description;
    private String mainImageUrl;
    private Long maxPeopleNumber;
    private int nowPeopleNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
