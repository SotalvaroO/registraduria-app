package soft.synergy.registraduriaapp.report.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.synergy.registraduriaapp.report.models.dtos.PollingLogsRequestDto;
import soft.synergy.registraduriaapp.report.models.dtos.ReportDto;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.services.IPollingLogsService;
import soft.synergy.registraduriaapp.report.utils.report.ReportExporterPDF;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/logs")
public class PollingLogsController {

    private final IPollingLogsService _logsService;

    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody PollingLogsRequestDto log) {
        return ResponseEntity.ok(_logsService.createLog(log));
    }

    @GetMapping(value = "/report")
    public void exportReportToPdf(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String date = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename=Report_" + date + ".pdf";

        response.setHeader(header,value);

        List<ReportDto> logs = _logsService.getReport();

        ReportExporterPDF exporter = new ReportExporterPDF(logs);
        exporter.export(response);

    }

}
