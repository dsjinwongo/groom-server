package com.example.groom.entity;

import com.example.groom.entity.common.CoopEntity;
import com.example.groom.entity.domain.room.Room;
import com.example.groom.entity.domain.todo.Todo;
import com.example.groom.entity.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Excuse extends CoopEntity {

    @Column
    private String content;

    @Column
    private LocalDateTime dueDate;

    @Column
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Long roomid;//bigint [ref: > room.id]

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo missedTodo;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

}