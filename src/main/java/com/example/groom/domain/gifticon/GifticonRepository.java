package com.example.groom.domain.gifticon;

import com.example.groom.entity.domain.gifticon.Gifticon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GifticonRepository extends JpaRepository<Gifticon, Long>, GifticonRepositoryCustom{
}
