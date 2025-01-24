package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.ChoiceDto;
import dev.rinesarusinovci.online_quizzes.mapper.ChoiceMapper;
import dev.rinesarusinovci.online_quizzes.mapper.QuestionMapper;
import dev.rinesarusinovci.online_quizzes.repositories.ChoiceRepository;
import dev.rinesarusinovci.online_quizzes.services.ChoiceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceMapper choiceMapper;
    private final ChoiceRepository choiceRepository;


    @Override
    public List<ChoiceDto> findAll() {
        return choiceMapper.toDtos(choiceRepository.findAll());
    }

    @Override
    public ChoiceDto findById(Long aLong) {
     return choiceMapper.toDto(choiceRepository.findById(aLong).orElse(null));
    }

    @Override
    public ChoiceDto add(ChoiceDto model) {
        return save(model);
    }

    @Override
    public ChoiceDto modify(Long id, ChoiceDto model) {

        if (id != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!choiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }
        return save(model);

    }

    @Override
    public void removeById(Long aLong) {

        if (!choiceRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        choiceRepository.deleteById(aLong);

    }
    private ChoiceDto save(ChoiceDto model) {
        var choiceEntity = choiceMapper.toEntity(model);
        var savedChoice = choiceRepository.save(choiceEntity);
        return choiceMapper.toDto(savedChoice);
    }
}
