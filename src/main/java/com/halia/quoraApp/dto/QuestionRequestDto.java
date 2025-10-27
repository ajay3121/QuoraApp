package com.halia.quoraApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDto {

    @NotBlank(message = "title is required")
    @Size(min = 10, max = 100, message = "title should be 10 to 100 characters long")
    private String title;

    @NotBlank(message = "content is required")
    @Size(min = 10, max = 100, message = "content should be 10 to 1000 characters long")
    private String content;
}
