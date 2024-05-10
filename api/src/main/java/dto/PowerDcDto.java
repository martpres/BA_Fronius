package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class PowerDcDto implements Serializable {

    private Float dcPowerPv;
    private ZonedDateTime timestamp;

    public PowerDcDto() {
    }

    public PowerDcDto(Float dcPowerPv, ZonedDateTime timestamp) {
        this.dcPowerPv = dcPowerPv;
        this.timestamp = timestamp;
    }

    public Float getDcPowerPv() {
        return dcPowerPv;
    }

    public void setDcPowerPv(Float dcPowerPv) {
        this.dcPowerPv = dcPowerPv;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PowerDcDto{" +
                "dcPowerPv=" + dcPowerPv +
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
            throw new IllegalStateException("powerDcDto class not found: " + site.get("P_PV").getClass());
        }
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
