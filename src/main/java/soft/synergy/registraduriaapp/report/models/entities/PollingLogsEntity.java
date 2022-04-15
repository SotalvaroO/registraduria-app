package soft.synergy.registraduriaapp.report.models.entities;

import lombok.*;
import soft.synergy.registraduriaapp.polling.models.entities.StandPerStationEntity;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "tbl_polling_logs")
public class PollingLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stand_per_station_id")
    private StandPerStationEntity standPerStationEntity;

    @Column(name = "total_polls")
    private long totalPolls;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public PollingLogsEntity() {
        this.dateTime = new Date();
    }


}
