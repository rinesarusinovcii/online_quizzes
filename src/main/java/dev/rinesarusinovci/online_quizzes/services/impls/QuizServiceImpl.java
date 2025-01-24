package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.mapper.QuizMapper;
import dev.rinesarusinovci.online_quizzes.repositories.QuizRepository;
import dev.rinesarusinovci.online_quizzes.services.QuizService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;

    @Override
    public List<QuizDto> findAll() {
        return quizMapper.toDtos(quizRepository.findAll());
    }

    @Override
    public QuizDto findById(Long aLong) {
        return quizMapper.toDto(quizRepository.findById(aLong).orElse(null));
    }

    @Override
    public QuizDto add(QuizDto model) {
        return save(model);
    }

    @Override
    public QuizDto modify(Long aLong, QuizDto model) {
         if (aLong != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!quizRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!quizRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        quizRepository.deleteById(aLong);
    }

    private QuizDto save(QuizDto model) {
        var quizEntity = quizMapper.toEntity(model);
        var savedQuiz = quizRepository.save(quizEntity);
        return quizMapper.toDto(savedQuiz);
    }
}
