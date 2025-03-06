package com.example.groom.domain.gifticon;

import com.example.groom.domain.gifticon.dto.GifticonDto;
import com.example.groom.domain.gifticon.dto.GifticonSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GifticonRepositoryCustom {

    Slice<GifticonDto> search(Pageable pageable, GifticonSearchCondition condition);
}
