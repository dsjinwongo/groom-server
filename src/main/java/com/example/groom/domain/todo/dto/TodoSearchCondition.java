package com.example.groom.domain.todo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class TodoSearchCondition {
    private Long userId;
    private Long roomId;
//    private Long roomSlotId;
//    private Long todoBoxId;
}
