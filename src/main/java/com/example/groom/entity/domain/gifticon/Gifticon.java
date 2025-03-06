package com.example.groom.entity.domain.gifticon;

import com.example.groom.domain.gifticon.dto.GifticonPostDto;
import com.example.groom.entity.common.OwnEntity;
import com.example.groom.entity.domain.auth.UserInfo;
import com.example.groom.entity.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gifticon extends OwnEntity {
    @JoinColumn
    @ManyToOne
    private UserInfo receiver;

    @Column
    private String message;

    @JoinColumn
    @ManyToOne
    private Product product;

    protected Gifticon(GifticonPostDto gifticonPostDto) {
        this.receiver = UserInfo.of(gifticonPostDto.getReceiverId());
        this.product = Product.of(gifticonPostDto.getProductId());
        this.message = gifticonPostDto.getMessage();
    }

    public static Gifticon of(GifticonPostDto gifticonPostDto) {
        return new Gifticon(gifticonPostDto);
    }
}
