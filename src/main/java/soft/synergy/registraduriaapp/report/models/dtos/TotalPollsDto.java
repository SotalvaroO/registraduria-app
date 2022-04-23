package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TotalPollsDto {

    private Long standPerStation;

    private long totalPolls;

}
