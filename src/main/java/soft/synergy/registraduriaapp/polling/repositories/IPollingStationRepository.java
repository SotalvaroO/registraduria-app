package soft.synergy.registraduriaapp.polling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;

import java.util.Optional;

@Repository
public interface IPollingStationRepository extends JpaRepository<PollingStationEntity, Long> {

    Optional<PollingStationEntity> findByCode(String code);
    Optional<PollingStationEntity> findByName(String name);

}
