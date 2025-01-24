package dev.rinesarusinovci.online_quizzes.dto;

import dev.rinesarusinovci.online_quizzes.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private long id;
    private String text;
    private QuestionType questionType;
    private double points;
    private long quizId; // Reference to the quiz ID instead of the full Quiz entity
    private List<String> choices;
}
