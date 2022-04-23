package soft.synergy.registraduriaapp.polling.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;
import soft.synergy.registraduriaapp.polling.services.IPollingStationService;
import soft.synergy.registraduriaapp.polling.services.IStandService;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/sites")
public class PollingSiteController {

    @Autowired
    private IPollingStationService _stationService;

    @Autowired
    private IStandService _standService;

    @GetMapping(value = "station")
    public ResponseEntity<?> getAllStations() {
        List<PollingStationDto> stations = _stationService.findAllStations();
        if (stations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stations);
    }


    @GetMapping(value = "station/{id}/stand")
    public ResponseEntity<?> findStandByStation(@PathVariable String id) {
        List<StandDto> stands = _standService.findAllStandsByStation(id);
        if (stands.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stands);
    }

}
