package soft.synergy.registraduriaapp.polling.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.synergy.registraduriaapp.polling.models.dtos.PollingStationDto;
import soft.synergy.registraduriaapp.polling.models.dtos.StandDto;
import soft.synergy.registraduriaapp.polling.services.IPollingStationService;
import soft.synergy.registraduriaapp.polling.services.IStandService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/sites")
public class PollingSiteController {

    private final IPollingStationService _stationService;

    private final IStandService _standService;

    @GetMapping(value = "station")
    public ResponseEntity<?> getAllStations(){
        List<PollingStationDto> stations = _stationService.findAllStations();
        if (stations.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stations);
    }

//    @GetMapping(value = "station/")
//    private ResponseEntity<?> getStationByCode(@RequestParam String name){
//        PollingStationDto station = _stationService.findByName(name);
//        if (station == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(station);
//    }

    @GetMapping(value = "station/{id}/stand")
    public ResponseEntity<?> findStandByStation(@PathVariable String id){
        List<StandDto> stands = _standService.findAllStandsByStation(id);
        return ResponseEntity.ok(stands);
    }

}
