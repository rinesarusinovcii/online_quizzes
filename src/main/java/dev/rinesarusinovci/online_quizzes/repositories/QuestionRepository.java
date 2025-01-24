package dev.rinesarusinovci.online_quizzes.repositories;

import dev.rinesarusinovci.online_quizzes.entities.Question;
import dev.rinesarusinovci.online_quizzes.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    Optional<Question> findById(Long id);

    Optional<Question> findByText(String text);

    Optional<Question> findByQuestionType(QuestionType questionType);
}
