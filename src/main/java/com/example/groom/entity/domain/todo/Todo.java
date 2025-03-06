package com.example.groom.entity.domain.todo;


import com.example.groom.domain.todo.dto.TodoDto;
import com.example.groom.domain.todo.dto.TodoFileDto;
import com.example.groom.domain.todo.dto.TodoUpdateDto;
import com.example.groom.entity.common.CoopEntity;
import com.example.groom.entity.domain.auth.UserInfo;
import com.example.groom.entity.domain.room.Room;
import com.example.groom.entity.enums.RoomSlotSample;
import com.querydsl.core.annotations.QueryInit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends CoopEntity {

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private RoomSlotSample roomSlot;

    @Column
    private String fileUrl;

    @Column
    private String fileName;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
//    private TodoBox todoBox;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @QueryInit("kakao.kakaoAccount.profile")
    private UserInfo todoOwner;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
//    private RoomSlot roomSlot;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations = new ArrayList<>();

    protected Todo(TodoDto todoDto) {
        this.title = todoDto.getTitle();
        this.content = todoDto.getContent();
        this.roomSlot = RoomSlotSample.todo;
        //this.todoBox = todoDto.getTodoBox();
        this.room = Room.of(todoDto.getRoomId());
        this.todoOwner = UserInfo.of(todoDto.getTodoOwnerId());
        //this.roomSlot = RoomSlot.of(todoDto.getRoomSlotId());
    }

    protected Todo(Long id) {
        super(id);
    }

    public static Todo of(TodoDto todoDto) {
        return new Todo(todoDto);
    }

    public static Todo of(Long id) {
        return new Todo(id);
    }

    public Todo of(RoomSlotSample roomSlot) {
        this.roomSlot = roomSlot;
        return this;
    }

    public Todo of(TodoFileDto todoFileDto) {
        this.fileUrl = todoFileDto.getFileUrl();
        this.fileName = todoFileDto.getFileName();
        return this;
    }

    public Todo of(TodoUpdateDto todoUpdateDto) {
        this.title = todoUpdateDto.getTitle();
        this.content = todoUpdateDto.getContent();
        this.roomSlot = todoUpdateDto.getRoomSlot();
        this.fileUrl = todoUpdateDto.getFileUrl();
        this.fileName = todoUpdateDto.getFileName();
        this.todoOwner = UserInfo.of(todoUpdateDto.getTodoOwnerId());

        return this;
    }
}
