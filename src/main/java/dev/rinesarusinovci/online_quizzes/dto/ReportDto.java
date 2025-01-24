package dev.rinesarusinovci.online_quizzes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private long id;
    private long quizId; // Reference to the associated quiz ID
    private double averageScore;
    private double highestScore;
    private double lowestScore;
    private LocalDate createdAt;
}
