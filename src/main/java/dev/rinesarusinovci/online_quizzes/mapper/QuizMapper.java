package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.QuizDto;
import dev.rinesarusinovci.online_quizzes.entities.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface QuizMapper extends BaseMapper<Quiz, QuizDto> {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    @Override
    @Mapping(source = "createdBy", target = "createdBy.id")
    @Mapping(source = "questions", target = "questions.id")
    @Mapping(source = "report", target = "report.id")
    @Mapping(source = "results", target = "results.id")
    Quiz toEntity(QuizDto quizDto);

    @Override
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "questions.id", target = "questions")
    @Mapping(source = "report.id", target = "report")
    @Mapping(source = "results.id", target = "results")
    QuizDto toDto(Quiz quiz);

    @Override
    @Mapping(source = "createdBy", target = "createdBy.id")
    @Mapping(source = "questions", target = "questions.id")
    @Mapping(source = "report", target = "report.id")
    @Mapping(source = "results", target = "results.id")
    List<Quiz> toEntities(List<QuizDto> quizDtos);

    @Override
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "questions.id", target = "questions")
    @Mapping(source = "report.id", target = "report")
    @Mapping(source = "results.id", target = "results")
    List<QuizDto> toDtos(List<Quiz> quizzes);
}
