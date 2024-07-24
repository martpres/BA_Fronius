package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class CommonInverterDataDto implements Serializable {
    private Float acPowerInverter;
    private Float dcVoltagePv;
    private Float acEnergyInverterDay;
    private ZonedDateTime timestamp;

    public CommonInverterDataDto() {
    }

    public CommonInverterDataDto(Float acPowerInverter, Float dcVoltagePv, Float acEnergyInverterDay, ZonedDateTime timestamp) {
        this.acPowerInverter = acPowerInverter;
        this.dcVoltagePv = dcVoltagePv;
        this.acEnergyInverterDay = acEnergyInverterDay;
        this.timestamp = timestamp;
    }

    public Float getAcPowerInverter() {
        return acPowerInverter;
    }

    public Float getDcVoltagePv() {
        return dcVoltagePv;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Float getAcEnergyInverterDay() {
        return acEnergyInverterDay;
    }

    @Override
    public String toString() {
        return "CommonInverterDataDto{" +
                ", acPowerInverter=" + acPowerInverter +
                ", dcVoltagePv=" + dcVoltagePv +
                ", acEnergyInverterDay=" + acEnergyInverterDay +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");
        Map<String, Object> pac = (Map<String, Object>) data.get("PAC");
        Map<String, Object> udc = (Map<String, Object>) data.get("UDC");
        Map<String, Object> dayEnergy = (Map<String, Object>) data.get("DAY_ENERGY");

        if (pac.get("Value").getClass() == Integer.class) {
            this.acPowerInverter = ((Integer) pac.get("Value")).floatValue();
        } else if (pac.get("Value").getClass() == Double.class) {
            this.acPowerInverter = ((Double) pac.get("Value")).floatValue();
        } else if (pac.get("Value").getClass() == BigDecimal.class) {
            this.acPowerInverter = ((BigDecimal) pac.get("Value")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class CommonInverterData (acPowerInverter): "
                    + pac.get("Value").getClass());
        }

        if (udc.get("Value").getClass() == Double.class) {
            this.dcVoltagePv = ((Double) udc.get("Value")).floatValue();
        } else if (udc.get("Value").getClass() == BigDecimal.class) {
            this.dcVoltagePv = ((BigDecimal) udc.get("Value")).floatValue();
        } else if (udc.get("Value").getClass() == Integer.class) {
            this.dcVoltagePv = ((Integer) udc.get("Value")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class CommonInverterData (dcVoltagePv): "
                    + udc.get("Value").getClass());
        }

        if (dayEnergy.get("Value").getClass() == Integer.class) {
            this.acEnergyInverterDay = ((Integer) dayEnergy.get("Value")).floatValue();
        } else if (dayEnergy.get("Value").getClass() == Double.class) {
            this.acEnergyInverterDay = ((Double) dayEnergy.get("Value")).floatValue();
        } else if (dayEnergy.get("Value").getClass() == BigDecimal.class) {
            this.acEnergyInverterDay = ((BigDecimal) dayEnergy.get("Value")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class PowerFlowRealtimeData (acEnergyInverterDay): "
                    + dayEnergy.get("Value").getClass());
        }

    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
