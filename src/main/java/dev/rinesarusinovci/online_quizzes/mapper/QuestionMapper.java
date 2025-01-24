package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface QuestionMapper extends BaseMapper<Question, QuestionDto> {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    @Mapping(source = "choices", target = "choices.id")
    List<Question> toEntities(List<QuestionDto> questionDtos);

    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "choices.id", target = "choices")
    List<QuestionDto> toDtos(List<Question> questions);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    @Mapping(source = "choices", target = "choices.id")
    Question toEntity(QuestionDto questionDto);

    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "choices.id", target = "choices")
    QuestionDto toDto(Question question);
}
