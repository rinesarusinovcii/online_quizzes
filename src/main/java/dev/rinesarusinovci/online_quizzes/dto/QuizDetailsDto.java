package dev.rinesarusinovci.online_quizzes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDetailsDto {
    private long id;
    private String title;
    private String description;
    private String category;
    private int timeLimit;
    private boolean isPublished;
    private LocalDate createdAt;
    private UserDto createdBy;
    private List<QuestionCreatedDto> questions;
}
