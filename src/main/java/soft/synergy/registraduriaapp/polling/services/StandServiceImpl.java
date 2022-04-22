package soft.synergy.registraduriaapp.polling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StandServiceImpl implements IStandService{

    private final IStandPerStationRepository _standRepository;


    @Override
    public List<StandDto> findAllStandsByStation(String stationId) {
        List<StandDto> standsDto= new ArrayList<>();
        List<StandPerStationEntity> standsPerStation = _standRepository.findAllByPollingStationCode(stationId);
        for (StandPerStationEntity s: standsPerStation
             ) {
            standsDto.add(new StandDto(s.getStand().getCode()));
        }

        return standsDto;
    }
}
