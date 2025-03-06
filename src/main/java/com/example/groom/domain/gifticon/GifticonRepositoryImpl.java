package com.example.groom.domain.gifticon;

import com.example.groom.domain.gifticon.dto.GifticonDto;
import com.example.groom.domain.gifticon.dto.GifticonSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.auth.QUserInfo.userInfo;
import static com.example.groom.entity.domain.gifticon.QGifticon.gifticon;
import static com.example.groom.entity.domain.product.QProduct.product;

public class GifticonRepositoryImpl implements GifticonRepositoryCustom{
    @Autowired
    private EntityManager em;

    private final JPAQueryFactory query;

    protected GifticonRepositoryImpl(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Slice<GifticonDto> search(Pageable pageable, GifticonSearchCondition condition) {
        List<GifticonDto> gifticonDtos = query.select(Projections.constructor(GifticonDto.class, gifticon))
                .join(gifticon.receiver, userInfo)
                .fetchJoin()
                .join(gifticon.product, product)
                .fetchJoin()
                .where(condition.getAllCondition())
                .fetch();

        Long count = query.select(gifticon.count()).from(gifticon).where(condition.getAllCondition())
                .fetchOne();
        return new PageImpl<>(gifticonDtos, pageable, count);
    }


}
