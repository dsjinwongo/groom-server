package com.example.groom.domain.tag;


import com.example.groom.domain.tag.dto.TagPostDto;
import com.example.groom.domain.tag.dto.TagSearchCondition;
import com.example.groom.domain.tag.dto.TagDto;
import com.example.groom.entity.domain.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping()
    public Slice<TagDto> searchTag(Pageable pageable, TagSearchCondition tagSearchCondition){
        return this.tagService.getTagAutoCompletes(pageable, tagSearchCondition);
    }

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable Long id){
        return this.tagService.getTagDetailById(id);
    }

    @PostMapping()
    public Tag createTag(@RequestBody TagPostDto tagPostDto){
        return this.tagService.createTag(tagPostDto);
    }

}
