package soft.synergy.registraduriaapp.report.services;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IStandPerStationRepository;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsResponseDto;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;
import soft.synergy.registraduriaapp.report.repositories.IPollingLogsRepository;
import soft.synergy.registraduriaapp.report.utils.mapper.LogsMapper;
import soft.synergy.registraduriaapp.report.utils.mapper.ReportMapper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class PollingLogsServiceImplTest {

    @Mock
    private IPollingLogsRepository _logsRepository;

    @Mock
    private IStandPerStationRepository _standPerStationRepository;

    private IPollingLogsService _logsService;

    @Mock
    private LogsMapper _logsMapper;

    @Mock
    private ReportMapper _reportMapper;

    private PollingLogsEntity log;

    private PollingLogsRequestDto logRequest;

    private PollingLogsResponseDto logResponse;

    private StandPerStationEntity standPerStation;

    private PollingStationEntity station;

    private StandEntity stand;

    private TotalPollsDto totalPolls;

    private ReportDto report;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this._logsService = new PollingLogsServiceImpl(_standPerStationRepository, _logsRepository, _logsMapper, _reportMapper);

        String dateString = "2022/04/22 11:11:11";
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = format.parse(dateString);

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

        log = PollingLogsEntity.builder()
                .id(1L)
                .totalPolls(255)
                .standPerStationEntity(standPerStation)
                //.dateTime(date)
                .build();


        logRequest = PollingLogsRequestDto.builder()
                .pollingStationCode("1")
                .standCode("1")
                .totalPolls(255)
                .build();

        logResponse = PollingLogsResponseDto.builder()
                .stationName(log.getStandPerStationEntity().getPollingStation().getName())
                .standNumber(log.getStandPerStationEntity().getStand().getCode())
                .stationAddress(log.getStandPerStationEntity().getPollingStation().getAddress())
                .totalPolls(log.getTotalPolls())
                .build();

        totalPolls = new TotalPollsDto(1L, 1);

        report = new ReportDto(log.getStandPerStationEntity().getPollingStation().getCode()
                , log.getStandPerStationEntity().getPollingStation().getName()
                , log.getStandPerStationEntity().getPollingStation().getAddress()
                , log.getStandPerStationEntity().getStand().getCode()
                , log.getTotalPolls());
    }

    @Test
    void createLog() {


        when(_standPerStationRepository.findByPollingStationCodeAndStandCode("1", "1"))
                .thenReturn(standPerStation);

        when(_logsMapper.dtoToModel(logRequest))
                .thenReturn(log);
        when(_logsRepository.save(log))
                .thenReturn(log);
        when(_logsMapper.modelToDto(log))
                .thenReturn(logResponse);
        PollingLogsResponseDto createdLog = _logsService.createLog(logRequest);
        Assertions.assertThat(createdLog.getTotalPolls()).isEqualTo(255);


    }

    @Test
    void getReport() {

        when(_logsRepository.countTotalPollsByStand())
                .thenReturn(Arrays.asList(totalPolls));
        when(_reportMapper.totalPollsToReportDto(totalPolls))
                .thenReturn(report);
        List<ReportDto> reports = _logsService.getReport();
        Assertions.assertThat(reports).isNotEmpty();


    }
}