package dev.rinesarusinovci.online_quizzes.repositories;

import dev.rinesarusinovci.online_quizzes.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByTitle(String title);

    Optional<Quiz> findByCategory(String category);
}
