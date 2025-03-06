package com.example.groom.domain.todo.evaluation;

import com.example.groom.domain.todo.evaluation.dto.EvaluationDto;
import com.example.groom.domain.todo.evaluation.dto.EvaluationListDto;
import com.example.groom.entity.domain.todo.Evaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    public void createEvaluation(EvaluationDto evaluationDto, Long todoId) {

        evaluationRepository.save(Evaluation.of(evaluationDto, todoId));
    }

    public List<EvaluationListDto> getEvaluationList(Long todoId) {

        List<Evaluation> evaluationList = evaluationRepository.findAllByTodoId(todoId);

        return evaluationList.stream()
                .map(EvaluationListDto::of)
                .toList();
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
