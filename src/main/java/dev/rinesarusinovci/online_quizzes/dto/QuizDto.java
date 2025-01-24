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
    private String createdBy; // Use userId or userName instead of the full User entity
    private List<String> questions; // Only include question IDs or summaries if needed
    private String report; // Reference to report ID or summary
    private List<String> results;
}
