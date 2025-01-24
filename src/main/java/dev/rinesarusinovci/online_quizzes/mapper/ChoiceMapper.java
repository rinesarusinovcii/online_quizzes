package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.ChoiceDto;
import dev.rinesarusinovci.online_quizzes.entities.Choice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ChoiceMapper extends BaseMapper<Choice, ChoiceDto> {
    ChoiceMapper INSTANCE = Mappers.getMapper(ChoiceMapper.class);

    @Override
    @Mapping(source = "questionId", target = "question.id")
    List<Choice> toEntities(List<ChoiceDto> choiceDtos);

    @Override
    List<ChoiceDto> toDtos(List<Choice> choices);

    @Override
    @Mapping(source = "question.id", target = "questionId")
    Choice toEntity(ChoiceDto choiceDto);

    @Override
    @Mapping(source = "question.id", target = "questionId")
    ChoiceDto toDto(Choice choice);





}
