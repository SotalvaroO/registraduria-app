package soft.synergy.registraduriaapp.polling.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.repositories.IPollingStationRepository;
import soft.synergy.registraduriaapp.polling.services.IPollingStationService;
import soft.synergy.registraduriaapp.polling.services.IStandService;
import soft.synergy.registraduriaapp.polling.utils.mapper.PollingStationMapper;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollingSiteController.class)
class PollingSiteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IPollingStationService _stationService;

    @MockBean
    private IStandService _standService;

    private PollingStationDto stationDto;

    private StandDto standDto;

    @BeforeEach
    void setUp() {



        stationDto = PollingStationDto.builder()
                .name("UDEA")
                .address("Brarranquilla")
                .code("1")
                .build();

        standDto = StandDto.builder()
                .code("1")
                .build();

    }

    @Test
    void getAllStations() throws Exception {
        mvc.perform(get("/api/sites/station")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        when(_stationService.findAllStations())
                .thenReturn(Arrays.asList(stationDto));
        mvc.perform(get("/api/sites/station")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findStandByStation() throws Exception {

        mvc.perform(get("/api/sites/station/1/stand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        when(_standService.findAllStandsByStation("1"))
                .thenReturn(Arrays.asList(standDto));
        mvc.perform(get("/api/sites/station/1/stand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}