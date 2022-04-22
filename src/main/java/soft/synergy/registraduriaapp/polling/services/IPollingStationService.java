package soft.synergy.registraduriaapp.polling.services;

import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;

import java.util.List;

public interface IPollingStationService {

    List<PollingStationDto> findAllStations();
    PollingStationDto findByCode(String code);
    PollingStationDto findByName(String name);

}
