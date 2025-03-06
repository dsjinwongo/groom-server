package com.example.groom.domain.gifticon.dto;


import com.example.groom.domain.auth.userInfo.dto.UserInfoDto;
import com.example.groom.domain.product.dto.ProductDto;
import com.example.groom.entity.domain.gifticon.Gifticon;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GifticonDto {
    //TODO private CompanyDto company;
    private Long id;
    private String message;
    private UserInfoDto receiver;

    private ProductDto product;

    protected GifticonDto(Gifticon gifticon) {
        this.message = gifticon.getMessage();
        this.receiver = UserInfoDto.of(gifticon.getReceiver());
        this.product = ProductDto.of(gifticon.getProduct());
        this.id = gifticon.getId();
    }

    public static GifticonDto of(Gifticon gifticon) {
        return new GifticonDto(gifticon);
    }
}
