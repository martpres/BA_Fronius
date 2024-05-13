package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class PowerFlowRealtimeDataDto implements Serializable {
    private Float dcPowerPv;
    private Float acPowerGrid;
    private ZonedDateTime timestamp;

    public PowerFlowRealtimeDataDto() {
    }

    public PowerFlowRealtimeDataDto(Float dcPowerPv, Float acPowerGrid, ZonedDateTime timestamp) {
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
        this.timestamp = timestamp;
    }

    public Float getDcPowerPv() {
        return dcPowerPv;
    }
    public void setDcPowerPv(Float dcPowerPv) {
        this.dcPowerPv = dcPowerPv;
    }
    public Float getAcPowerGrid() {
        return acPowerGrid;
    }
    public void setAcPowerGrid(Float acPowerGrid) {
        this.acPowerGrid = acPowerGrid;
    }
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PowerFlowRealtimeDataDto{" +
                "dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", timestamp=" + timestamp +
                '}';
    }

@JsonProperty("Body")
private void unpackData(Map<String, Object> body) {
    Map<String, Object> data = (Map<String, Object>) body.get("Data");
    Map<String, Object> site = (Map<String, Object>) data.get("Site");

    if (site.get("P_PV").getClass() == Double.class) {
        this.dcPowerPv = ((Double) site.get("P_PV")).floatValue();
    } else if (site.get("P_PV").getClass() == BigDecimal.class) {
        this.dcPowerPv = ((BigDecimal) site.get("P_PV")).floatValue();
    } else {
        throw new IllegalStateException("Unexpected class for P_PV: " + site.get("P_PV").getClass());
    }

    if (site.get("P_Grid").getClass() == Double.class) {
        this.acPowerGrid = ((Double) site.get("P_Grid")).floatValue();
    } else if (site.get("P_Grid").getClass() == BigDecimal.class) {
        this.acPowerGrid = ((BigDecimal) site.get("P_Grid")).floatValue();
    } else {
        throw new IllegalStateException("Unexpected class for P_Grid: " + site.get("P_Grid").getClass());
    }
}

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
