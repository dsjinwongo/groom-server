package com.example.groom.entity.domain.todo;

import com.example.groom.domain.todo.evaluation.dto.EvaluationDto;
import com.example.groom.entity.common.OwnEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evaluation extends OwnEntity {

    @Column
    private String comment;

    @Column
    private String score;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo; // 평가 받는 Todo

    protected Evaluation(EvaluationDto evaluationDto, Long todoId) {
        this.comment = evaluationDto.getComment();
        this.score = evaluationDto.getScore();
        this.todo = Todo.of(todoId);
    }

    public static Evaluation of(EvaluationDto evaluationDto, Long todoId) {
        return new Evaluation(evaluationDto, todoId);
    }
}
