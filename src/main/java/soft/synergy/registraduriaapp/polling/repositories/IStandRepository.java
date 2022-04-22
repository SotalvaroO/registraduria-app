package soft.synergy.registraduriaapp.polling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft.synergy.registraduriaapp.polling.models.entities.StandEntity;

@Repository
public interface IStandRepository extends JpaRepository<StandEntity, Long> {

    StandEntity findByCode(String code);

}
