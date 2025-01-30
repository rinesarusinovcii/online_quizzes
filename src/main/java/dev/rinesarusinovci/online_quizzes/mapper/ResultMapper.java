package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.ResultDto;
import dev.rinesarusinovci.online_quizzes.entities.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;


@Mapper(componentModel = "spring")

public interface ResultMapper extends BaseMapper<Result, ResultDto> {
    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    ResultDto toDto(Result result);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    Result toEntity(ResultDto resultDto);

    @Override
    List<ResultDto> toDtos(List<Result> results);

    @Override
    List<Result> toEntities(List<ResultDto> resultDtos);


}
