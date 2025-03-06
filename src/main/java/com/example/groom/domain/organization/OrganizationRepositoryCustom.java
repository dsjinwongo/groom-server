package com.example.groom.domain.organization;

import com.example.groom.domain.organization.dto.OrganizationDto;
import com.example.groom.domain.organization.dto.OrganizationPostDto;
import com.example.groom.domain.organization.dto.OrganizationSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface OrganizationRepositoryCustom {
    Slice<OrganizationDto> search(Pageable pageable, OrganizationSearchCondition condition);

    OrganizationDto update(Long id, OrganizationPostDto organizationPostDto);
}
