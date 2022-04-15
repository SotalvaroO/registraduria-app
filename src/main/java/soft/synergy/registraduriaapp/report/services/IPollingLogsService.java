package soft.synergy.registraduriaapp.report.services;

import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;

import java.util.List;

public interface IPollingLogsService {

    PollingLogsEntity createLog(PollingLogsRequestDto log);

    List<ReportDto> getReport();

}
