package com.example.groom.domain.tag;


import com.example.groom.domain.tag.dto.TagDto;
import com.example.groom.domain.tag.dto.TagSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TagRepositoryCustom {

    TagDto getTagDetailDto(Long id);

    Slice<TagDto> search(Pageable pageable, TagSearchCondition tagSearchCondition);
}
