package com.example.groom.domain.gifticon;

import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.domain.gifticon.dto.GifticonDto;
import com.example.groom.domain.gifticon.dto.GifticonPostDto;
import com.example.groom.domain.gifticon.dto.GifticonSearchCondition;
import com.example.groom.entity.domain.gifticon.Gifticon;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GifticonService {

    private final GifticonRepository gifticonRepository;

    public GifticonDto send(GifticonPostDto gifticonPostDto) {
        Gifticon gifticon = Gifticon.of(gifticonPostDto);
        gifticon = this.gifticonRepository.save(gifticon);
        return GifticonDto.of(gifticon);
    }

    public Slice<GifticonDto> search(Pageable pageable, GifticonSearchCondition condition) {
        return this.gifticonRepository.search(pageable, condition);
    }

    public GifticonDto findById(Long id) {
        Gifticon gifticon = this.gifticonRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.GIFTICON_NOT_FOUND));
        return GifticonDto.of(gifticon);

    }

    public Long deleteById(Long id) {
        this.gifticonRepository.deleteById(id);
        return id;
    }
}
