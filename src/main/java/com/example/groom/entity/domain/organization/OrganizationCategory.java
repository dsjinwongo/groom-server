package com.example.groom.entity.domain.organization;

import com.example.groom.entity.domain.category.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrganizationCategory extends Category {
    public OrganizationCategory(Long categoryId) {
        super(categoryId);
    }

    public static OrganizationCategory of(Long categoryId) {
        return new OrganizationCategory(categoryId);
    }
}
