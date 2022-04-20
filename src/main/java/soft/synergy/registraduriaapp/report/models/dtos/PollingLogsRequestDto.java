package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollingLogsRequestDto {

    private String pollingStationCode;

    private String standCode;

    private long totalPolls;

}
