package soft.synergy.registraduriaapp.report.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import soft.synergy.registraduriaapp.polling.controllers.PollingSiteController;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;
import soft.synergy.registraduriaapp.polling.services.IPollingStationService;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsResponseDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;
import soft.synergy.registraduriaapp.report.services.IPollingLogsService;
import soft.synergy.registraduriaapp.report.utils.mapper.LogsMapper;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollingLogsController.class)
class PollingLogsControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper om;

    private PollingLogsRequestDto requestDto;

    private PollingLogsResponseDto responseDto;


    @MockBean
    private IPollingLogsService _logsService;

    @BeforeEach
    void setUp() {

        requestDto = PollingLogsRequestDto.builder()
                .pollingStationCode("1")
                .standCode("1")
                .totalPolls(1)
                .build();

        responseDto = PollingLogsResponseDto.builder()
                .standNumber("1")
                .stationName("UDEA")
                .stationAddress("Barranquilla")
                .dateTime(new Date())
                .totalPolls(1)
                .build();



        om = new ObjectMapper();

    }

    @Test
    void createLog() throws Exception {

        String jsonRequest = om.writeValueAsString(requestDto);

        when(_logsService.createLog(requestDto))
                .thenReturn(responseDto);


        mvc.perform(post("/api/logs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isInternalServerError());



    }

    @Test
    void exportReportToPdf() throws Exception {

        mvc.perform(get("/api/logs/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}