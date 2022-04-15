package soft.synergy.registraduriaapp.report.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;

@Component
@RequiredArgsConstructor
public class LogsMapper {

    private final IStandPerStationRepository _standPerStationRepository;

    private final IPollingStationRepository _pollingStationRepository;

    private final IStandRepository _standRepository;

    public PollingLogsEntity dtoToModel(PollingLogsRequestDto logDto) {
        PollingLogsEntity log = new PollingLogsEntity();

        log.setTotalPolls(logDto.getTotalPolls());
        log.setStandPerStationEntity(_standPerStationRepository.findByPollingStationAndStand(_pollingStationRepository.findByCode(logDto.getPollingStationCode()), _standRepository.findByCode(logDto.getStandCode())));

        return log;
    }

}
