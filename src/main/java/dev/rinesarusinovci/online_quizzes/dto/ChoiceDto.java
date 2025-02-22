package dev.rinesarusinovci.online_quizzes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDto {
    private long id;
    private String text;
    private long questionId;
    private boolean correct;
}
