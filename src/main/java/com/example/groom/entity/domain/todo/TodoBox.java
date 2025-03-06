package com.example.groom.entity.domain.todo;

import com.example.groom.entity.common.CoopEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoBox extends CoopEntity {
    //할일 서랍
    @Column
    private String name;

    @Column
    private String colorHex;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime dueDate;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Long room_id;
}
