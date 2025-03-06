package com.example.groom.domain.todo.evaluation.dto;

import com.example.groom.entity.domain.todo.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvaluationListDto {

    private Long id;

    private String nickname;

    private String profileImage;

    private String score;

    private String comment;

    private Long ownerId;

    public static EvaluationListDto of(Evaluation evaluation) {
        return new EvaluationListDto(
                evaluation.getId(),
                evaluation.getOwner().getKakao().getKakaoAccount().getProfile().getNickname(),
                evaluation.getOwner().getKakao().getKakaoAccount().getProfile().getProfileImageUrl(),
                evaluation.getScore(),
                evaluation.getComment(),
                evaluation.getOwner().getId()
        );
    }
}
