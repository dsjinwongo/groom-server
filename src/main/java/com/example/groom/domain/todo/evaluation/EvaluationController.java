package com.example.groom.domain.todo.evaluation;

import com.example.groom.domain.todo.evaluation.dto.EvaluationDto;
import com.example.groom.domain.todo.evaluation.dto.EvaluationListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo/evaluation/{todoId}")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping
    public Long createEvaluation(@RequestBody EvaluationDto evaluationDto, @PathVariable Long todoId) {

        evaluationService.createEvaluation(evaluationDto, todoId);

        return todoId;
    }

    @GetMapping
    public List<EvaluationListDto> getEvaluationList(@PathVariable Long todoId) {

        return evaluationService.getEvaluationList(todoId);
    }

    @DeleteMapping("/{evaluationId}")
    public Long deleteEvaluation(@PathVariable Long evaluationId, @PathVariable Long todoId) {
        evaluationService.deleteEvaluation(evaluationId);

        return todoId;
    }
}
