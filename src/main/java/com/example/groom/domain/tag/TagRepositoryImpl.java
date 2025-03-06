package com.example.groom.domain.tag;


import com.example.groom.domain.tag.dto.TagDto;
import com.example.groom.domain.tag.dto.TagSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.tag.QTag.tag;

public class TagRepositoryImpl implements TagRepositoryCustom {

    @Autowired
    private EntityManager em;

    private final JPAQueryFactory query;

    public TagRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public TagDto getTagDetailDto(Long id) {
        return query.select(Projections.constructor(TagDto.class, tag.id, tag.name
        )).from(tag).where(tag.id.eq(id)).fetchOne();
    }

    @Override
    public Slice<TagDto> search(Pageable pageable, TagSearchCondition tagSearchCondition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!tagSearchCondition.getName().isEmpty()) builder.and(tag.name.contains(tagSearchCondition.getName()));
        if (tagSearchCondition.getDateLoe() != null) builder.and(tag.updatedAt.loe(tagSearchCondition.getDateLoe()));
        if (tagSearchCondition.getDateGoe() != null) builder.and(tag.updatedAt.goe(tagSearchCondition.getDateGoe()));
        List<TagDto> tagDtos = query
                .select(Projections
                        .constructor(TagDto.class, tag.id, tag.name))
                .from(tag)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        int size = query.selectFrom(tag).where(builder).fetch().size();

        return new PageImpl<>(tagDtos, pageable, size);
    }
}
