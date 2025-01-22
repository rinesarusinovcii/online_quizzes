package dev.rinesarusinovci.online_quizzes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be a positive number")
    private long id;

    @Column(nullable = false,length = 2000)
    @NotNull(message = "Text is required")
    @NotBlank(message = "Text is required")
    @Size(min = 10, max = 2000, message = "Text must be between 10 and 2000 characters")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    @NotNull(message = "Is correct is required")
    @NotBlank(message = "Is correct is required")
    private boolean isCorrect;

}
