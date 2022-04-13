package soft.synergy.registraduriaapp.report.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;

@Repository
public interface IPollingLogsRepository extends JpaRepository<PollingLogsEntity, Long> {
}
