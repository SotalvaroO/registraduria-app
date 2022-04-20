package soft.synergy.registraduriaapp.polling.utils.mapper;

import org.springframework.stereotype.Component;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;

@Component
public class PollingStationMapper {

    public PollingStationDto modelToDto(PollingStationEntity pollingStation){
        PollingStationDto stationDto = new PollingStationDto();
        stationDto.setCode(pollingStation.getCode());
        stationDto.setName(pollingStation.getName());
        stationDto.setAddress(pollingStation.getAddress());

        return stationDto;
    }

}
