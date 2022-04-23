package soft.synergy.registraduriaapp.polling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;

import java.util.List;

@Repository
public interface IStandPerStationRepository extends JpaRepository<StandPerStationEntity, Long> {

    StandPerStationEntity findByPollingStationAndStand(PollingStationEntity station, StandEntity stand);

    @Query("SELECT u FROM StandPerStationEntity u WHERE u.pollingStation.code = ?1 and u.stand.code = ?2")
    StandPerStationEntity findByPollingStationCodeAndStandCode(String stationCode, String standCode);

    List<StandPerStationEntity> findAllByPollingStationCode(String code);

}
