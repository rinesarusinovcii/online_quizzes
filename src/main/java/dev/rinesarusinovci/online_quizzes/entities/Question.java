package dev.rinesarusinovci.online_quizzes.entities;

import dev.rinesarusinovci.online_quizzes.enums.QuestionType;
import dev.rinesarusinovci.online_quizzes.infrastructure.Contains;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be a positive number")
    private long id;

    @Column(nullable = false,length = 2000)
    @NotNull(message = "Question is required")
    @NotBlank(message = "Question is required")
    @Size(min = 10, max = 2000, message = "Question must be between 10 and 2000 characters")
    private String text;

    @Column(nullable = false)
    @NotNull(message = "Question type is required")
    @NotBlank(message = "Question type is required")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(nullable = false)
    @NotNull(message = "Points is required")
    @PositiveOrZero(message = "Points must be a positive number")
    private double points;

    @ManyToOne
    @JoinColumn(name = "quiz_id",nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices;
}
