package com.halia.quoraApp.services;

import com.halia.quoraApp.dto.QuestionRequestDto;
import com.halia.quoraApp.dto.QuestionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {

    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionRequestDto);

    public Mono<QuestionResponseDto> getQuestionById(String id);

    public Flux<QuestionResponseDto> getAllQuestions();

    public Mono<Void> deleteQuestionById(String id);
}
