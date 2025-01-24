package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.dto.ResultDto;
import dev.rinesarusinovci.online_quizzes.mapper.ResultMapper;
import dev.rinesarusinovci.online_quizzes.repositories.ResultRepository;
import dev.rinesarusinovci.online_quizzes.services.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultMapper resultMapper;
    private final ResultRepository resultRepository;

    @Override
    public List<ResultDto> findAll() {
        return resultMapper.toDtos(resultRepository.findAll());
    }

    @Override
    public ResultDto findById(Long aLong) {
        return resultMapper.toDto(resultRepository.findById(aLong).orElse(null));
    }

    @Override
    public ResultDto add(ResultDto model) {
        return save(model);
    }

    @Override
    public ResultDto modify(Long aLong, ResultDto model) {
        if (aLong != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!resultRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!resultRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        resultRepository.deleteById(aLong);

    }

    private ResultDto save(ResultDto model) {
        var resultEntity = resultMapper.toEntity(model);
        var savedResult = resultRepository.save(resultEntity);
        return resultMapper.toDto(savedResult);
    }
}
