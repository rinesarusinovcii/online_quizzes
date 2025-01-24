package dev.rinesarusinovci.online_quizzes.mapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BaseMapper<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);

    List<TEntity> toEntities(List<TDto> dtos);

    List<TDto> toDtos(List<TEntity> entities);
}
