package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class CurrentAcDto implements Serializable {

    private Float acPhase1;
    private Float acPhase2;
    private Float acPhase3;
    private ZonedDateTime timestamp;

    public CurrentAcDto() {
    }

    public CurrentAcDto(Float acPhase1, Float acPhase2, Float acPhase3, ZonedDateTime timestamp) {
        this.acPhase1 = acPhase1;
        this.acPhase2 = acPhase2;
        this.acPhase3 = acPhase3;
        this.timestamp = timestamp;
    }

    public Float getAcPhase1() {
        return acPhase1;
    }

    public void setAcPhase1(Float acPhase1) {
        this.acPhase1 = acPhase1;
    }

    public Float getAcPhase2() {
        return acPhase2;
    }

    public void setAcPhase2(Float acPhase2) {
        this.acPhase2 = acPhase2;
    }

    public Float getAcPhase3() {
        return acPhase3;
    }

    public void setAcPhase3(Float acPhase3) {
        this.acPhase3 = acPhase3;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CurrentAcDto{" +
                "acPhase1=" + acPhase1 +
                ", acPhase2=" + acPhase2 +
                ", acPhase3=" + acPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String,Object> data = (Map<String, Object>) body.get("Data");
        this.acPhase1 = ((BigDecimal) data.get("Current_AC_Phase_1")).floatValue();
        this.acPhase2 = ((BigDecimal) data.get("Current_AC_Phase_2")).floatValue();
        this.acPhase3 = ((BigDecimal) data.get("Current_AC_Phase_3")).floatValue();
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
