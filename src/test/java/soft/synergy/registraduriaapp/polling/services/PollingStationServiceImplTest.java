package soft.synergy.registraduriaapp.polling.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.utils.mapper.PollingStationMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class PollingStationServiceImplTest {

    @Mock
    private IPollingStationRepository _stationRepository;

    private IPollingStationService _stationService;

    private PollingStationMapper _stationMapper;

    private PollingStationEntity station;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this._stationMapper = new PollingStationMapper();
        this._stationService = new PollingStationServiceImpl(_stationRepository, _stationMapper);

        station = PollingStationEntity.builder()
                .id(1L)
                .code("1")
                .name("UDEA0")
                .address("Cl. 67 ##53-108, Medellín, Antioquia")
                .build();
    }

    @Test
    void findAllStations() {

        when(_stationRepository.findAll())
                .thenReturn(Arrays.asList(station));
        List<PollingStationDto> stationsFound = _stationService.findAllStations();
        Assertions.assertThat(stationsFound).isNotEmpty();

    }
}