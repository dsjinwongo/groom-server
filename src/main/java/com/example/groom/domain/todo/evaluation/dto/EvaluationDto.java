package com.example.groom.domain.todo.evaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvaluationDto {

    private String score;

    private String comment;
}
