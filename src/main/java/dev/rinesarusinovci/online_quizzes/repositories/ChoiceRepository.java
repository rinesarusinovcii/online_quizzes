package dev.rinesarusinovci.online_quizzes.repositories;

import dev.rinesarusinovci.online_quizzes.entities.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Optional<Choice> findById(Long id);
}
