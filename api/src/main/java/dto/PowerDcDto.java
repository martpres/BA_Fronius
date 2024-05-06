package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class PowerDcDto implements Serializable {

    private Float dcPower1;
    private ZonedDateTime timestamp;

    public PowerDcDto() {
    }

    public PowerDcDto(Float dcPower1, ZonedDateTime timestamp) {
        this.dcPower1 = dcPower1;
        this.timestamp = timestamp;
    }

    public Float getDcPower1() {
        return dcPower1;
    }

    public void setDcPower1(Float dcPower1) {
        this.dcPower1 = dcPower1;
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
                "dcPower1=" + dcPower1 +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String,Object> data = (Map<String, Object>) body.get("Data");
        if (data.get("P_PV").getClass()==Double.class){
            this.dcPower1 = ((Double) data.get("P_PV")).floatValue();
            return;
        }
        if (data.get("P_PV").getClass()==BigDecimal.class){
            this.dcPower1 = ((BigDecimal) data.get("P_PV")).floatValue();
            return;
        }
        throw new IllegalStateException("powerDcDto class not found: " + data.get("P_PV").getClass());
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
