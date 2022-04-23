package soft.synergy.registraduriaapp.report.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsResponseDto;
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

    private final IStandPerStationRepository _standPerStationRepository;

    private final IPollingLogsRepository _logsRepository;

    private final LogsMapper _logsMapper;

    private final ReportMapper _reportMapper;

    @Override
    public PollingLogsResponseDto createLog(PollingLogsRequestDto log) {

        StandPerStationEntity standPerStation = _standPerStationRepository.findByPollingStationCodeAndStandCode(log.getPollingStationCode(),log.getStandCode());
        if (standPerStation != null){
            PollingLogsEntity logEntity = _logsMapper.dtoToModel(log);
            PollingLogsEntity logSaved = _logsRepository.save(logEntity);
            return _logsMapper.modelToDto(logSaved);
        }
        return null;
    }

    @Override
    public List<ReportDto> getReport() {

        List<TotalPollsDto> logs = _logsRepository.countTotalPollsByStand();

        List<ReportDto> report = new ArrayList<>();

        for (TotalPollsDto p : logs
        ) {
            report.add(_reportMapper.totalPollsToReportDto(p));
        }

        return report;

    }
}
