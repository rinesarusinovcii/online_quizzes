package dev.rinesarusinovci.online_quizzes.repositories;

import dev.rinesarusinovci.online_quizzes.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Override
    Optional<Report> findById(Long id);

    Optional<Report> findByQuizId(Long id);
}
