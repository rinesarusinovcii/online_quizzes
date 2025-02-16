package dev.rinesarusinovci.online_quizzes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(mappedBy = "report")
//    private Quiz quiz;
    @Column(nullable = false)
    @NotNull(message = "Average score is required")
    @Positive
    private double averageScore;

    @Column(nullable = false)
    @NotNull(message = "Highest score is required")
    @Positive
    private double highestScore;

    @Column(nullable = false)
    @NotNull(message = "Lowest score is required")
    @PastOrPresent(message = "Created at must be in the past or present")
    private LocalDate createdAt;
}
