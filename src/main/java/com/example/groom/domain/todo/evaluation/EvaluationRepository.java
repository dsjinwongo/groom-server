package com.example.groom.domain.todo.evaluation;

import com.example.groom.entity.domain.todo.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByTodoId(Long todoId);
}
