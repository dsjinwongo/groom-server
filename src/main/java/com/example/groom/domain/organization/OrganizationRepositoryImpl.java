package com.example.groom.domain.organization;

import com.example.groom.domain.organization.dto.OrganizationDto;
import com.example.groom.domain.organization.dto.OrganizationPostDto;
import com.example.groom.domain.organization.dto.OrganizationSearchCondition;
import com.example.groom.entity.domain.organization.OrganizationCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.groom.entity.domain.organization.QOrganization.organization;
import static com.example.groom.entity.domain.organization.QOrganizationCategory.organizationCategory;

public class OrganizationRepositoryImpl implements OrganizationRepositoryCustom{

    @Autowired
    private EntityManager em;

    private final JPAQueryFactory query;

    protected OrganizationRepositoryImpl(EntityManager em){
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Slice<OrganizationDto> search(Pageable pageable, OrganizationSearchCondition condition) {
        List<OrganizationDto> fetch = query.select(Projections.constructor(OrganizationDto.class, organization.id, organization.name, organization.description, organization.category))
                .from(organization)
                .join(organizationCategory)
                .fetchJoin()
                .where(condition.getAllCondition())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(organization.count())
                .from(organization)
                .where(condition.getAllCondition())
                .fetchOne();
        return new PageImpl<>(fetch,pageable, count);
    }

    @Override
    public OrganizationDto update(Long id, OrganizationPostDto organizationPostDto) {
        JPAUpdateClause update = query.update(organization);
        if(organizationPostDto.getCategoryId() != null)
            update.set(organization.category, OrganizationCategory.of(organizationPostDto.getCategoryId()));
        if(organizationPostDto.getName() != null && !organizationPostDto.getName().isBlank())
            update.set(organization.name, organizationPostDto.getName());
        if(organizationPostDto.getDescription() != null && !organizationPostDto.getDescription().isBlank())
            update.set(organization.description, organizationPostDto.getDescription());
        long execute = update.where(organization.id.eq(id)).execute();
        OrganizationDto organizationDto = query.select(Projections.constructor(OrganizationDto.class, organization)).from(organization).where(organization.id.eq(id))
                .fetchOne();
        return organizationDto;
    }


}
