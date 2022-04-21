package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PollingLogsRequestDto {

    @NotBlank(message = "El codigo del lugar de votacion es obligatorio")
    private String pollingStationCode;

    @NotBlank(message = "El puesto de votacion es obligatorio")
    private String standCode;

    @Positive(message = "La cantidad de votos debe ser mayor a cero")
    @NotNull(message = "La cantidad de votos es obligatoria")
    private long totalPolls;

}
