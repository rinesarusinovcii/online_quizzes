package dev.rinesarusinovci.online_quizzes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Quiz quiz;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Score must be a positive number")
    private double score;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Correct answers must be a positive number")
    private int correctAnswers;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Wrong answers must be a positive number")
    private int wrongAnswers;

    @Column(nullable = false)
    @NotNull
    private boolean isPassed;
}
