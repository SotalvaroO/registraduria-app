package soft.synergy.registraduriaapp.report.models.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

@Builder
public class ReportDto {

    private String stationId;

    private String name;

    private String address;

    private String standId;

    private long totalPolls;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public long getTotalPolls() {
        return totalPolls;
    }

    public void setTotalPolls(long totalPolls) {
        this.totalPolls = totalPolls;
    }
}
