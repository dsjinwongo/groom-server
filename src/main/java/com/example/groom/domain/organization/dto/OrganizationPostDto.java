package com.example.groom.domain.organization.dto;

import lombok.Data;

@Data
public class OrganizationPostDto {
    private String name;
    private String description;
    private Long categoryId;
}
