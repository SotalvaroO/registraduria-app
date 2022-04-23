package soft.synergy.registraduriaapp.report.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsResponseDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;

@Component
@RequiredArgsConstructor
public class LogsMapper {

    @Autowired
    private IStandPerStationRepository _standPerStationRepository;


    public PollingLogsEntity dtoToModel(PollingLogsRequestDto logDto) {
        PollingLogsEntity log = new PollingLogsEntity();

        log.setTotalPolls(logDto.getTotalPolls());
        StandPerStationEntity f = _standPerStationRepository.findByPollingStationCodeAndStandCode(logDto.getPollingStationCode(), logDto.getStandCode());
        log.setStandPerStationEntity(f);

        return log;
    }

    public PollingLogsResponseDto modelToDto(PollingLogsEntity log) {
        PollingLogsResponseDto logDto = new PollingLogsResponseDto();
        logDto.setStationName(log.getStandPerStationEntity().getPollingStation().getName());
        logDto.setStationAddress(log.getStandPerStationEntity().getPollingStation().getAddress());
        logDto.setStandNumber(log.getStandPerStationEntity().getStand().getCode());
        logDto.setTotalPolls(log.getTotalPolls());
        logDto.setDateTime(log.getDateTime());

        return logDto;

    }

}
