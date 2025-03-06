package com.example.groom.domain.organization;

import com.example.groom.domain.organization.dto.OrganizationDto;
import com.example.groom.domain.organization.dto.OrganizationPostDto;
import com.example.groom.domain.organization.dto.OrganizationSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping()
    public Slice<OrganizationDto> search(Pageable pageable, OrganizationSearchCondition condition){
        return this.organizationService.search(pageable, condition);
    }

    @GetMapping("/{id}")
    public OrganizationDto getDetail(@PathVariable Long id){
        return this.organizationService.findOneById(id);
    }

    @DeleteMapping("/{id}")
    public Long deleteOne(@PathVariable Long id){
        return this.organizationService.deleteById(id);
    }

    @PostMapping()
    public OrganizationDto save(@RequestBody OrganizationPostDto organizationPostDto){
        return this.organizationService.save(organizationPostDto);
    }

    @PatchMapping("/{id}")
    public OrganizationDto update(@RequestBody OrganizationPostDto organizationPostDto, @PathVariable Long id){
        return this.organizationService.update(id, organizationPostDto);
    }
}
