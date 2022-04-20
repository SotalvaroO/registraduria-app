package soft.synergy.registraduriaapp.polling.services;

import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;

import java.util.List;

public interface IStandService {

    List<StandDto> findAllStandsByStation(String stationId);

}
