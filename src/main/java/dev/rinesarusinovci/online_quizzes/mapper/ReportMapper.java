package dev.rinesarusinovci.online_quizzes.mapper;

import dev.rinesarusinovci.online_quizzes.dto.ReportDto;
import dev.rinesarusinovci.online_quizzes.entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ReportMapper extends BaseMapper<Report, ReportDto> {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    List<Report> toEntities(List<ReportDto> reportDtos);

    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    List<ReportDto> toDtos(List<Report> reports);

    @Override
    @Mapping(source = "quizId", target = "quiz.id")
    Report toEntity(ReportDto reportDto);

    @Override
    @Mapping(source = "quiz.id", target = "quizId")
    ReportDto toDto(Report report);



}
