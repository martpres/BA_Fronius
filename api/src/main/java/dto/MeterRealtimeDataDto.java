package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class MeterRealtimeDataDto implements Serializable {
    private Float acCurrentGridPhase1;
    private Float acCurrentGridPhase2;
    private Float acCurrentGridPhase3;
    private ZonedDateTime timestamp;

    public MeterRealtimeDataDto() {
    }

    public MeterRealtimeDataDto(Float acCurrentGridPhase1, Float acCurrentGridPhase2, Float acCurrentGridPhase3, ZonedDateTime timestamp) {
        this.acCurrentGridPhase1 = acCurrentGridPhase1;
        this.acCurrentGridPhase2 = acCurrentGridPhase2;
        this.acCurrentGridPhase3 = acCurrentGridPhase3;
        this.timestamp = timestamp;
    }

    public Float getAcCurrentGridPhase1() {
        return acCurrentGridPhase1;
    }

    public void setAcCurrentGridPhase1(Float acCurrentGridPhase1) {
        this.acCurrentGridPhase1 = acCurrentGridPhase1;
    }

    public Float getAcCurrentGridPhase2() {
        return acCurrentGridPhase2;
    }

    public void setAcCurrentGridPhase2(Float acCurrentGridPhase2) {
        this.acCurrentGridPhase2 = acCurrentGridPhase2;
    }

    public Float getAcCurrentGridPhase3() {
        return acCurrentGridPhase3;
    }

    public void setAcCurrentGridPhase3(Float acCurrentGridPhase3) {
        this.acCurrentGridPhase3 = acCurrentGridPhase3;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MeterRealtimeDataDto{" +
                "acCurrentGridPhase1=" + acCurrentGridPhase1 +
                ", acCurrentGridPhase2=" + acCurrentGridPhase2 +
                ", acCurrentGridPhase3=" + acCurrentGridPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");
        if (data.get("Current_AC_Phase_1").getClass() == Double.class) {
            this.acCurrentGridPhase1 = ((Double) data.get("Current_AC_Phase_1")).floatValue();
            this.acCurrentGridPhase2 = ((Double) data.get("Current_AC_Phase_2")).floatValue();
            this.acCurrentGridPhase3 = ((Double) data.get("Current_AC_Phase_3")).floatValue();
        } else if (data.get("Current_AC_Phase_1").getClass() == BigDecimal.class) {
            this.acCurrentGridPhase1 = ((BigDecimal) data.get("Current_AC_Phase_1")).floatValue();
            this.acCurrentGridPhase2 = ((BigDecimal) data.get("Current_AC_Phase_2")).floatValue();
            this.acCurrentGridPhase3 = ((BigDecimal) data.get("Current_AC_Phase_3")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData :" + data.get("Current_AC_Phase_1").getClass());
        }
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
