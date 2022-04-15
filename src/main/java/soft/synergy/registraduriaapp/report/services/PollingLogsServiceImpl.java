package soft.synergy.registraduriaapp.report.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;
import soft.synergy.registraduriaapp.report.repositories.IPollingLogsRepository;
import soft.synergy.registraduriaapp.report.utils.mapper.LogsMapper;
import soft.synergy.registraduriaapp.report.utils.mapper.ReportMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollingLogsServiceImpl implements IPollingLogsService {

    private final IPollingLogsRepository _logsRepository;

    private final LogsMapper _logsMapper;

    private final ReportMapper _reportMapper;

    @Override
    public PollingLogsEntity createLog(PollingLogsRequestDto log) {
        return _logsRepository.save(_logsMapper.dtoToModel(log));
    }

    @Override
    public List<ReportDto> getReport() {

        List<TotalPollsDto> logs = _logsRepository.countTotalPollsByStand();

        List<ReportDto> report = new ArrayList<>();

        for (TotalPollsDto p: logs
             ) {
            report.add(_reportMapper.totalPollsToReportDto(p));
        }

        return report;

    }
}
