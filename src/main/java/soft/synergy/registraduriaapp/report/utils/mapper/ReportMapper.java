package soft.synergy.registraduriaapp.report.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;

@Component
@RequiredArgsConstructor
public class ReportMapper {

    @Autowired
    private IStandPerStationRepository _standPerStationRepository;

    @Autowired
    private IPollingStationRepository _pollingStationRepository;

    @Autowired
    private IStandRepository _standRepository;

    public ReportDto totalPollsToReportDto(TotalPollsDto totalPolls) {
        ReportDto report = new ReportDto();

        StandPerStationEntity standPerStation = _standPerStationRepository.findById(totalPolls.getStandPerStation()).orElse(null);

        PollingStationEntity pollingStation = _pollingStationRepository.findById(standPerStation.getPollingStation().getId()).orElse(null);

        StandEntity stand = _standRepository.findById(standPerStation.getStand().getId()).orElse(null);

        report.setStationId(pollingStation.getCode());
        report.setName(pollingStation.getName());
        report.setAddress(pollingStation.getAddress());
        report.setStandId(stand.getCode());
        report.setTotalPolls(totalPolls.getTotalPolls());

        return report;

    }

}
