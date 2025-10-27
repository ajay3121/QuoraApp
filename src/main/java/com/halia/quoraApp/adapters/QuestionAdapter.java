package com.halia.quoraApp.adapters;

import com.halia.quoraApp.dto.QuestionResponseDto;
import com.halia.quoraApp.models.Question;

public class QuestionAdapter {

    public static QuestionResponseDto toQuestionResponseDto(Question question){
        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
