package com.example.groom.domain.gifticon.dto;

import lombok.Data;

@Data
public class GifticonPostDto {
    private String message;
    private Long productId;
    private Long receiverId;

}
