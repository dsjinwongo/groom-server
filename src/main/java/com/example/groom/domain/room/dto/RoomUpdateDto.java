package com.example.groom.domain.room.dto;


import lombok.Data;

@Data
public class RoomUpdateDto {
    private Long id;
    private String name;
    private String description;
    private String mainImageUrl;
    private Long maxPeople;
}
