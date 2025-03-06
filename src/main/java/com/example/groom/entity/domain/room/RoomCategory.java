package com.example.groom.entity.domain.room;

import com.example.groom.entity.domain.category.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomCategory extends Category {


    protected RoomCategory(Long id) {
        super(id);
    }

    static public RoomCategory of(Long id){
        return new RoomCategory(id);
    }
}
