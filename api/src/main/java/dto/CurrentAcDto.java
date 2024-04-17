package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CurrentAcDto implements Serializable {
    @JsonProperty("Current_AC_Phase_1")
    private Float acPhase1;
    @JsonProperty("Current_AC_Phase_2")
    private Float acPhase2;
    @JsonProperty("Current_AC_Phase_3")
    private Float acPhase3;
    private LocalDateTime timestamp;

    public CurrentAcDto() {
    }

    public CurrentAcDto(Float acPhase1, Float acPhase2, Float acPhase3, LocalDateTime timestamp) {
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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
}
