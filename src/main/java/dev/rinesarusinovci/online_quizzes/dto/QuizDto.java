package dev.rinesarusinovci.online_quizzes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
    private long id;
    private String title;
    private String description;
    private String category;
    private boolean isPublished;
    private LocalDate createdAt;
    private int timeLimit;
    private long createdBy;
    private List<Long> questions;
    private long report;
    private List<Long> results;
}
