package com.example.groom.entity.domain.organization;

import com.example.groom.domain.organization.dto.OrganizationPostDto;
import com.example.groom.entity.common.BaseEntity;
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
public class Organization extends BaseEntity {
    @Column
    private String name;

    @Column
    private String description;

    @JoinColumn
    @ManyToOne
    private OrganizationCategory category;

    protected Organization(Long organizationId) {
        super(organizationId);
    }

    public Organization(OrganizationPostDto organizationPostDto) {
        this.category = OrganizationCategory.of(organizationPostDto.getCategoryId());
        this.name = organizationPostDto.getName();
        this.description = organizationPostDto.getDescription();
    }

    public static Organization of(Long organizationId) {
        return new Organization(organizationId);
    }

    public static Organization of(OrganizationPostDto organizationPostDto) {
        return new Organization(organizationPostDto);
    }
}
