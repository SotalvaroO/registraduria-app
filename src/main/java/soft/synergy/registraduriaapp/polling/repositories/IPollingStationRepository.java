package soft.synergy.registraduriaapp.polling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.polling.models.entities.PollingStationEntity;

@Repository
public interface IPollingStationRepository extends JpaRepository<PollingStationEntity, Long> {

    PollingStationEntity findByCode(String code);

}
