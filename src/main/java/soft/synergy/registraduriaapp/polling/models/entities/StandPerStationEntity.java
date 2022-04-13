package soft.synergy.registraduriaapp.polling.models.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tbl_stand_per_station")
public class StandPerStationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stand_id")
    private StandEntity stand;

    @ManyToOne
    @JoinColumn(name = "polling_station_id")
    private PollingStationEntity pollingStation;

}
