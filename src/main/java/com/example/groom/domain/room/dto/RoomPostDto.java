package com.example.groom.domain.room.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomPostDto {
    private String name;

    private String description;

    private String mainImageUrl;

    private Long maxPeople;

    private List<Long> roomParticipants = new ArrayList<>();
}
