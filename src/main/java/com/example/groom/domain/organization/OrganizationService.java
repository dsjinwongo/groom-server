package com.example.groom.domain.organization;


import com.example.groom.common.exception.CustomException;
import com.example.groom.domain.organization.dto.OrganizationDto;
import com.example.groom.domain.organization.dto.OrganizationPostDto;
import com.example.groom.domain.organization.dto.OrganizationSearchCondition;
import com.example.groom.entity.domain.organization.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import static com.example.groom.common.exception.ErrorCode.ORGANIZATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Slice<OrganizationDto> search(Pageable pageable, OrganizationSearchCondition condition) {
        return this.organizationRepository.search(pageable, condition);
    }

    public OrganizationDto findOneById(Long id) {
        Organization organization = this.organizationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));
        return OrganizationDto.of(organization);
    }

    public Long deleteById(Long id) {
        this.organizationRepository.deleteById(id);
        return id;
    }

    public OrganizationDto save(OrganizationPostDto organizationPostDto) {
        Organization organization = Organization.of(organizationPostDto);
        organization = this.organizationRepository.save(organization);
        return OrganizationDto.of(organization);
    }

    public OrganizationDto update(Long id, OrganizationPostDto organizationPostDto) {
        return this.organizationRepository.update(id, organizationPostDto);
    }
}
