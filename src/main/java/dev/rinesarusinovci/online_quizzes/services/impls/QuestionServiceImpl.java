package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.ChoiceDto;
import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.mapper.QuestionMapper;
import dev.rinesarusinovci.online_quizzes.repositories.QuestionRepository;
import dev.rinesarusinovci.online_quizzes.services.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionDto> findAll() {
        return questionMapper.toDtos(questionRepository.findAll());
    }

    @Override
    public QuestionDto findById(Long aLong) {
        return questionMapper.toDto(questionRepository.findById(aLong).orElse(null));
    }

    @Override
    public QuestionDto add(QuestionDto model) {
        return save(model);
    }

    @Override
    public QuestionDto modify(Long aLong, QuestionDto model) {
        if (aLong != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!questionRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!questionRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        questionRepository.deleteById(aLong);
    }


    private QuestionDto save(QuestionDto model) {
        var choiceEntity = questionMapper.toEntity(model);
        var savedChoice = questionRepository.save(choiceEntity);
        return questionMapper.toDto(savedChoice);
    }
}
