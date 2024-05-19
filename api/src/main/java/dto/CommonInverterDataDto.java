package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class CommonInverterDataDto implements Serializable {
    private Integer acPowerInverter;
    private Float dcVoltagePv;
    private ZonedDateTime timestamp;

    public CommonInverterDataDto() {
    }

    public CommonInverterDataDto(Integer acPowerInverter, Float dcVoltagePv, ZonedDateTime timestamp) {
        this.acPowerInverter = acPowerInverter;
        this.dcVoltagePv = dcVoltagePv;
        this.timestamp = timestamp;
    }

    public Integer getAcPowerInverter() {
        return acPowerInverter;
    }

    public void setAcPowerInverter(Integer acPowerInverter) {
        this.acPowerInverter = acPowerInverter;
    }

    public Float getDcVoltagePv() {
        return dcVoltagePv;
    }

    public void setDcVoltagePv(Float dcVoltagePv) {
        this.dcVoltagePv = dcVoltagePv;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CommonInverterDataDto{" +
                ", acPowerInverter=" + acPowerInverter +
                "dcVoltagePv=" + dcVoltagePv +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");
        Map<String, Object> pac = (Map<String, Object>) data.get("PAC");
        Map<String, Object> udc = (Map<String, Object>) data.get("UDC");
        if (pac.get("Value").getClass() == Integer.class) {
            this.acPowerInverter = ((Integer) pac.get("Value")).intValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class CommonInverterData: " + pac.get("Value").getClass());
        }
        if (udc.get("Value").getClass() == Double.class) {
            this.dcVoltagePv = ((Double) udc.get("Value")).floatValue();
        } else if (udc.get("Value").getClass() == BigDecimal.class) {
            this.dcVoltagePv = ((BigDecimal) udc.get("Value")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class CommonInverterData: " + udc.get("Value").getClass());
        }
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }
}
