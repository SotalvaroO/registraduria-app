package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollingLogsRequestDto {

    private String pollingStationCode;

    private String standCode;

    private long totalPolls;

}
