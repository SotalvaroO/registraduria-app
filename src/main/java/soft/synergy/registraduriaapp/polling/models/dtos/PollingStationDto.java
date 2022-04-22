package soft.synergy.registraduriaapp.polling.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PollingStationDto {

    private String code;

    private String name;

    private String address;

}
