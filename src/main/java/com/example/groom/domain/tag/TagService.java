package com.example.groom.domain.tag;


import com.example.groom.domain.tag.dto.TagDto;
import com.example.groom.domain.tag.dto.TagPostDto;
import com.example.groom.domain.tag.dto.TagSearchCondition;
import com.example.groom.entity.domain.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;



    public TagDto getTagDetailById(Long id) {
        return this.tagRepository.getTagDetailDto(id);
    }

    public Slice<TagDto> getTagAutoCompletes(Pageable pageable, TagSearchCondition tagSearchCondition){
        return this.tagRepository.search(pageable, tagSearchCondition);
    }

    public Tag createTag(TagPostDto tagPostDto) {
        Tag tag = Tag.of(tagPostDto);
        return this.tagRepository.save(tag);
    }

    public List<Tag> createTagByList(List<TagPostDto> tagPostDtos){
        List<Tag> tags = tagPostDtos.stream().map(Tag::of).toList();
        return this.tagRepository.saveAllAndFlush(tags);
    }

}
