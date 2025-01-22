package dev.rinesarusinovci.online_quizzes.dto;

import dev.rinesarusinovci.online_quizzes.entities.Choice;
import dev.rinesarusinovci.online_quizzes.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreatedDto {
    @NotBlank(message = "Question text is required")
    private String text;

    private QuestionType questionType;

    private double points;

    @NotNull(message = "Quiz ID is required")
    @PositiveOrZero(message = "Quiz ID must be a positive number")
    private long quizId;

    @NotNull(message = "Answer options are required")
    private List<Choice> choices;


}
