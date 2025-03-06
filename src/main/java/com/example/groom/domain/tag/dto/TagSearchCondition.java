package com.example.groom.domain.tag.dto;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.example.groom.entity.domain.tag.QTag.tag;

@Data
public class TagSearchCondition {
    private String name;
    private LocalDateTime dateLoe;
    private LocalDateTime dateGoe;

    public TagSearchCondition(String name, LocalDateTime dateLoe, LocalDateTime dateGoe) {
        this.name = name;
        this.dateLoe = dateLoe;
        this.dateGoe = dateGoe;
    }

    private BooleanBuilder createdAtBetween() {
        BooleanBuilder builder = new BooleanBuilder();
        if (dateGoe != null)
            builder.and(tag.createdAt.after(dateGoe));
        if(dateGoe != null)
            builder.and(tag.createdAt.before(dateLoe));
        return builder;
    }

    private BooleanBuilder nameContains() {
        BooleanBuilder builder = new BooleanBuilder();
        if(!name.isBlank())builder.and(tag.name.contains(name));
        return builder;
    }

    public BooleanBuilder getAllCondition() {
        return new BooleanBuilder().and(createdAtBetween()).and(nameContains());
    }
}
