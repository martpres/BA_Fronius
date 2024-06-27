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
    private Float acPowerGridPhase1;
    private Float acPowerGridPhase2;
    private Float acPowerGridPhase3;

    private ZonedDateTime timestamp;

    public MeterRealtimeDataDto() {
    }

    public MeterRealtimeDataDto(Float acCurrentGridPhase1, Float acCurrentGridPhase2, Float acCurrentGridPhase3,
                                Float acPowerGridPhase1, Float acPowerGridPhase2, Float acPowerGridPhase3,
                                ZonedDateTime timestamp) {
        this.acCurrentGridPhase1 = acCurrentGridPhase1;
        this.acCurrentGridPhase2 = acCurrentGridPhase2;
        this.acCurrentGridPhase3 = acCurrentGridPhase3;
        this.acPowerGridPhase1 = acPowerGridPhase1;
        this.acPowerGridPhase2 = acPowerGridPhase2;
        this.acPowerGridPhase3 = acPowerGridPhase3;
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

    public Float getAcPowerGridPhase1() {
        return acPowerGridPhase1;
    }

    public void setAcPowerGridPhase1(Float acPowerGridPhase1) {
        this.acPowerGridPhase1 = acPowerGridPhase1;
    }

    public Float getAcPowerGridPhase2() {
        return acPowerGridPhase2;
    }

    public void setAcPowerGridPhase2(Float acPowerGridPhase2) {
        this.acPowerGridPhase2 = acPowerGridPhase2;
    }

    public Float getAcPowerGridPhase3() {
        return acPowerGridPhase3;
    }

    public void setAcPowerGridPhase3(Float acPowerGridPhase3) {
        this.acPowerGridPhase3 = acPowerGridPhase3;
    }

    @Override
    public String toString() {
        return "MeterRealtimeDataDto{" +
                "acCurrentGridPhase1=" + acCurrentGridPhase1 +
                ", acCurrentGridPhase2=" + acCurrentGridPhase2 +
                ", acCurrentGridPhase3=" + acCurrentGridPhase3 +
                ", acPowerGridPhase1=" + acPowerGridPhase1 +
                ", acPowerGridPhase2=" + acPowerGridPhase2 +
                ", acPowerGridPhase3=" + acPowerGridPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");

        if (data.get("Current_AC_Phase_1").getClass() == Double.class) {
            this.acCurrentGridPhase1 = ((Double) data.get("Current_AC_Phase_1")).floatValue();
        } else if (data.get("Current_AC_Phase_1").getClass() == BigDecimal.class) {
            this.acCurrentGridPhase1 = ((BigDecimal) data.get("Current_AC_Phase_1")).floatValue();
        } else if (data.get("Current_AC_Phase_1").getClass() == Integer.class) {
            this.acCurrentGridPhase1 = ((Integer) data.get("Current_AC_Phase_1")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (Current_AC_Phase_1):"
                    + data.get("Current_AC_Phase_1").getClass());
        }

        if (data.get("Current_AC_Phase_2").getClass() == Double.class) {
            this.acCurrentGridPhase2 = ((Double) data.get("Current_AC_Phase_2")).floatValue();
        } else if (data.get("Current_AC_Phase_2").getClass() == BigDecimal.class) {
            this.acCurrentGridPhase2 = ((BigDecimal) data.get("Current_AC_Phase_2")).floatValue();
        } else if (data.get("Current_AC_Phase_2").getClass() == Integer.class) {
            this.acCurrentGridPhase2 = ((Integer) data.get("Current_AC_Phase_2")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (Current_AC_Phase_2):"
                    + data.get("Current_AC_Phase_2").getClass());
        }

        if (data.get("Current_AC_Phase_3").getClass() == Double.class) {
            this.acCurrentGridPhase3 = ((Double) data.get("Current_AC_Phase_3")).floatValue();
        } else if (data.get("Current_AC_Phase_3").getClass() == BigDecimal.class) {
            this.acCurrentGridPhase3 = ((BigDecimal) data.get("Current_AC_Phase_3")).floatValue();
        } else if (data.get("Current_AC_Phase_3").getClass() == Integer.class) {
            this.acCurrentGridPhase3 = ((Integer) data.get("Current_AC_Phase_3")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (Current_AC_Phase_3):"
                    + data.get("Current_AC_Phase_3").getClass());
        }

        if (data.get("PowerReal_P_Phase_1").getClass() == Double.class) {
            this.acPowerGridPhase1 = ((Double) data.get("PowerReal_P_Phase_1")).floatValue();
        } else if (data.get("PowerReal_P_Phase_1").getClass() == BigDecimal.class) {
            this.acPowerGridPhase1 = ((BigDecimal) data.get("PowerReal_P_Phase_1")).floatValue();
        } else if (data.get("PowerReal_P_Phase_1").getClass() == Integer.class) {
            this.acPowerGridPhase1 = ((Integer) data.get("PowerReal_P_Phase_1")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (PowerReal_P_Phase_1):"
                    + data.get("PowerReal_P_Phase_1").getClass());
        }

        if (data.get("PowerReal_P_Phase_2").getClass() == Double.class) {
            this.acPowerGridPhase2 = ((Double) data.get("PowerReal_P_Phase_2")).floatValue();
        } else if (data.get("PowerReal_P_Phase_2").getClass() == BigDecimal.class) {
            this.acPowerGridPhase2 = ((BigDecimal) data.get("PowerReal_P_Phase_2")).floatValue();
        } else if (data.get("PowerReal_P_Phase_2").getClass() == Integer.class) {
            this.acPowerGridPhase2 = ((Integer) data.get("PowerReal_P_Phase_2")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (PowerReal_P_Phase_2):"
                    + data.get("PowerReal_P_Phase_2").getClass());
        }

        if (data.get("PowerReal_P_Phase_3").getClass() == Double.class) {
            this.acPowerGridPhase3 = ((Double) data.get("PowerReal_P_Phase_3")).floatValue();
        } else if (data.get("PowerReal_P_Phase_3").getClass() == BigDecimal.class) {
            this.acPowerGridPhase3 = ((BigDecimal) data.get("PowerReal_P_Phase_3")).floatValue();
        } else if (data.get("PowerReal_P_Phase_3").getClass() == Integer.class) {
            this.acPowerGridPhase3 = ((Integer) data.get("PowerReal_P_Phase_3")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class MeterRealtimeData (PowerReal_P_Phase_3):"
                    + data.get("PowerReal_P_Phase_3").getClass());
        }

    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
