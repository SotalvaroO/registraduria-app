package soft.synergy.registraduriaapp.report.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto;
import soft.synergy.registraduriaapp.report.models.entities.PollingLogsEntity;

import java.util.List;

@Repository
public interface IPollingLogsRepository extends JpaRepository<PollingLogsEntity, Long> {

    @Query("SELECT new soft.synergy.registraduriaapp.report.models.dtos.TotalPollsDto(u.standPerStationEntity.id, SUM(u.totalPolls))" + "FROM PollingLogsEntity AS u GROUP BY u.standPerStationEntity.id")
    List<TotalPollsDto> countTotalPollsByStand();

}
