package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.entities.Choice;
import dev.rinesarusinovci.online_quizzes.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;


@Mapper(componentModel = "spring")
public interface QuestionMapper extends BaseMapper<Question, QuestionDto> {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);


    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "choices", target = "choices")
    QuestionDto toDto(Question question);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    @Mapping(source = "choices", target = "choices")
    Question toEntity(QuestionDto questionDto);

    @Override
    List<QuestionDto> toDtos(List<Question> questions);

    @Override
    List<Question> toEntities(List<QuestionDto> questionDtos);

    // Custom mapping methods for lists

    default List<Long> mapChoicesToIds(List<Choice> choices) {
        if (choices == null) return null;
        return choices.stream()
                .map(Choice::getId)
                .toList(); // Zëvendëson Collectors.toList()
    }

    default List<Choice> mapIdsToChoices(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Choice choice = new Choice();
                    choice.setId(id);
                    return choice;
                })
                .toList(); // Zëvendëson Collectors.toList()
    }
}
