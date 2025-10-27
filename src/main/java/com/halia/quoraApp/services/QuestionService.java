package com.halia.quoraApp.services;

import com.halia.quoraApp.adapters.QuestionAdapter;
import com.halia.quoraApp.dto.QuestionRequestDto;
import com.halia.quoraApp.dto.QuestionResponseDto;
import com.halia.quoraApp.models.Question;
import com.halia.quoraApp.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionRequestDto) {
        Question question = Question.builder().
                title(questionRequestDto.getTitle()).
                content(questionRequestDto.getContent()).
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();

        return questionRepository.save(question).
                map(QuestionAdapter::toQuestionResponseDto).
                doOnSuccess(response -> System.out.println("question created successfully:"+ response)).
                doOnError(error -> System.out.println("error occurred" + error));
    }

    @Override
    public Mono<QuestionResponseDto> getQuestionById(String id){
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDto)
                .doOnSuccess(response -> System.out.println("question fetched successfully: " + response))
                .doOnError(error -> System.out.println("failed to fetch question   " + error));
    }


    @Override
    public Flux<QuestionResponseDto> getAllQuestions(){
        return questionRepository.findAll()
                .map(QuestionAdapter::toQuestionResponseDto)
                .doOnComplete(() -> System.out.println("questions fetched successfully"))
                .doOnError(error -> System.out.println("error occurred while fetching: " + error));
    }

    public Mono<Void> deleteQuestionById(String id){
        return questionRepository.deleteById(id)
                .doOnSuccess(response -> System.out.println("question deleted successfully: " + id))
                .doOnError(error -> System.out.println("error while deleting question: " + error));
    }
}
