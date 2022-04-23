package soft.synergy.registraduriaapp.polling.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.polling.repositories.IStandRepository;
import soft.synergy.registraduriaapp.polling.utils.mapper.PollingStationMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class StandServiceImplTest {

    @Mock
    private IStandPerStationRepository _standRepository;

    private IStandService _standService;

    private StandEntity stand;

    private PollingStationEntity station;

    private StandPerStationEntity standPerStation;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        this._standService = new StandServiceImpl(_standRepository);

        station = PollingStationEntity.builder()
                .id(1L)
                .code("1")
                .name("UDEA0")
                .address("Cl. 67 ##53-108, Medellín, Antioquia")
                .build();

        stand = StandEntity.builder()
                .id(1L)
                .code("1")
                .build();

        standPerStation = StandPerStationEntity.builder()
                .id(1L)
                .stand(stand)
                .pollingStation(station)
                .build();

    }

    @Test
    void findAllStandsByStation() {
        when(_standRepository.findAllByPollingStationCode("1"))
                .thenReturn(Arrays.asList(standPerStation));
        List<StandDto> stands = _standService.findAllStandsByStation("1");
        Assertions.assertThat(stands).isNotEmpty();
    }
}