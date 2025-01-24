package dev.rinesarusinovci.online_quizzes.services.impls;

import dev.rinesarusinovci.online_quizzes.dto.QuestionDto;
import dev.rinesarusinovci.online_quizzes.dto.ReportDto;
import dev.rinesarusinovci.online_quizzes.dto.ResultDto;
import dev.rinesarusinovci.online_quizzes.mapper.ReportMapper;
import dev.rinesarusinovci.online_quizzes.repositories.ReportRepository;
import dev.rinesarusinovci.online_quizzes.services.ReportService;
import dev.rinesarusinovci.online_quizzes.services.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;


    @Override
    public List<ReportDto> findAll() {
        return reportMapper.toDtos(reportRepository.findAll());
    }

    @Override
    public ReportDto findById(Long aLong) {
        return reportMapper.toDto(reportRepository.findById(aLong).orElse(null));
    }

    @Override
    public ReportDto add(ReportDto model) {
        return save(model);
    }

    @Override
    public ReportDto modify(Long aLong, ReportDto model) {
        if (aLong != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!reportRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!reportRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        reportRepository.deleteById(aLong);

    }

    private ReportDto save(ReportDto model) {
        var reportEntity = reportMapper.toEntity(model);
        var savedReport = reportRepository.save(reportEntity);
        return reportMapper.toDto(savedReport);
    }
}
