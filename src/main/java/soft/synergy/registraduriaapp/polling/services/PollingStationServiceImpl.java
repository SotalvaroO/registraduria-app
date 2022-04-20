package soft.synergy.registraduriaapp.polling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.utils.mapper.PollingStationMapper;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PollingStationServiceImpl implements IPollingStationService{

    private final IPollingStationRepository _stationRepository;

    private final PollingStationMapper _stationMapper;

    @Override
    public List<PollingStationDto> findAllStations() {
        List<PollingStationDto> stationsDto = new ArrayList<>();
        List<PollingStationEntity> stations = _stationRepository.findAll();
        for (PollingStationEntity s:stations
             ) {
            stationsDto.add(_stationMapper.modelToDto(s));
        }
        return stationsDto;
    }

    @Override
    public PollingStationDto findByCode(String code) {
        PollingStationEntity station = _stationRepository.findByCode(code).orElse(null);
        if (station == null){
            return null;
        }
        return _stationMapper.modelToDto(station);
    }

    @Override
    public PollingStationDto findByName(String name) {
        PollingStationEntity station = _stationRepository.findByName(name).orElse(null);
        if (station == null){
            return null;
        }
        return _stationMapper.modelToDto(station);
    }
}
