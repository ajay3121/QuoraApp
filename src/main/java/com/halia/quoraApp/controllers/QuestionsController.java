package com.halia.quoraApp.controllers;

import com.halia.quoraApp.dto.QuestionRequestDto;
import com.halia.quoraApp.dto.QuestionResponseDto;
import com.halia.quoraApp.services.IQuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto questionRequestDto){
        return questionService.createQuestion(questionRequestDto)
                .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @GetMapping("{id}")
    public Mono<QuestionResponseDto> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id)
                .doOnSuccess(response -> System.out.println("Question fetched successfully: " + response))
                .doOnError(error -> System.out.println("Error fetching question: " + error));
    }

    @GetMapping()
    public Flux<QuestionResponseDto> getAllQuestions(){
        return questionService.getAllQuestions()
                .doOnComplete(() -> System.out.println("fetched questions successfully"))
                .doOnError(error -> System.out.println("error occurred while fetching questions: " + error));
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteQuestionById(@PathVariable String id){
        return questionService.deleteQuestionById(id);
    }
}
