package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class MeterRealtimeDataDto implements Serializable {

    private Float acPhase1;
    private Float acPhase2;
    private Float acPhase3;
    private ZonedDateTime timestamp;

    public MeterRealtimeDataDto() {
    }

    public MeterRealtimeDataDto(Float acPhase1, Float acPhase2, Float acPhase3, ZonedDateTime timestamp) {
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
        return "MeterRealtimeDataDto{" +
                "acPhase1=" + acPhase1 +
                ", acPhase2=" + acPhase2 +
                ", acPhase3=" + acPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String,Object> data = (Map<String, Object>) body.get("Data");
        if (data.get("Current_AC_Phase_1").getClass()==Double.class){
            this.acPhase1 = ((Double) data.get("Current_AC_Phase_1")).floatValue();
            this.acPhase2 = ((Double) data.get("Current_AC_Phase_2")).floatValue();
            this.acPhase3 = ((Double) data.get("Current_AC_Phase_3")).floatValue();
        }
        else if (data.get("Current_AC_Phase_1").getClass()==BigDecimal.class){
            this.acPhase1 = ((BigDecimal) data.get("Current_AC_Phase_1")).floatValue();
            this.acPhase2 = ((BigDecimal) data.get("Current_AC_Phase_2")).floatValue();
            this.acPhase3 = ((BigDecimal) data.get("Current_AC_Phase_3")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData :"  + data.get("Current_AC_Phase_1").getClass());
        }
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
