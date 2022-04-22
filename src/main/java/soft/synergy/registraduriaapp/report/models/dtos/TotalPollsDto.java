package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class TotalPollsDto {

    private Long standPerStation;

    private long totalPolls;

}
