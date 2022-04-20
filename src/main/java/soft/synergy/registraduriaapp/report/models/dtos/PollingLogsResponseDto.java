package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollingLogsResponseDto {

    private String stationName;
    private String stationAddress;
    private String standNumber;
    private long totalPolls;
    private Date dateTime;


}
