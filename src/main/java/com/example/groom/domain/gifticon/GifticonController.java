package com.example.groom.domain.gifticon;


import com.example.groom.domain.gifticon.dto.GifticonDto;
import com.example.groom.domain.gifticon.dto.GifticonPostDto;
import com.example.groom.domain.gifticon.dto.GifticonSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gifticon")
public class GifticonController {
    private final GifticonService gifticonService;

    @PostMapping()
    public GifticonDto save(@RequestBody GifticonPostDto gifticonPostDto){
        return this.gifticonService.send(gifticonPostDto);
    }

    @GetMapping()
    public Slice<GifticonDto> search(Pageable pageable, GifticonSearchCondition condition){
        return this.gifticonService.search(pageable, condition);
    }

    @GetMapping("/{id}")
    public GifticonDto findById(@PathVariable Long id){
        return this.gifticonService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id){
        return this.gifticonService.deleteById(id);
    }
}
