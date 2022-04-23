package soft.synergy.registraduriaapp.report.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsResponseDto;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.services.IPollingLogsService;
import soft.synergy.registraduriaapp.report.utils.report.ReportExporterPDF;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/logs")
public class PollingLogsController {

    @Autowired
    private IPollingLogsService _logsService;


    @PostMapping
    public ResponseEntity<?> createLog(@Valid @RequestBody PollingLogsRequestDto log, BindingResult result) {

        Map<String, Object> response = new HashMap<>();


        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> {
                        return "The field " + err.getField() + " " + err.getDefaultMessage();
                    }).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        PollingLogsResponseDto logToCreate = new PollingLogsResponseDto();
        try {
            logToCreate = _logsService.createLog(log);
            if (logToCreate == null) {
                response.put("Error", "Could not find stations or/and stand with that values");
                response.put("message", "Fail");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            response.put("message", "error during creating in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Success");
        response.put("body", logToCreate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/report")
    public String exportReportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Report_" + date + ".pdf";

        response.setHeader(header, value);

        List<ReportDto> logs = _logsService.getReport();

        ReportExporterPDF exporter = new ReportExporterPDF(logs);
        exporter.export(response);

        return "Report_" + date + ".pdf";

    }

}
