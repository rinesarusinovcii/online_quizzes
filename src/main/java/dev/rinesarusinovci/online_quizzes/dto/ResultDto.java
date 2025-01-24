package dev.rinesarusinovci.online_quizzes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private long id;
    private long quizId; // Reference to the associated quiz ID
    private double score;
    private int correctAnswers;
    private int wrongAnswers;
    private boolean isPassed;
}
