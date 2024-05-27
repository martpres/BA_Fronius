package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class PowerFlowRealtimeDataDto implements Serializable {
    private Float dcPowerPv;
    private Float acPowerGrid;
    private Float dcPowerAkku;
    private Float acPowerLoad;
    private Float autonomy;
    private Float selfConsumption;
    private Float stateOfChargeAkku;
    private ZonedDateTime timestamp;

    public PowerFlowRealtimeDataDto() {
    }

    public PowerFlowRealtimeDataDto(Float dcPowerPv, Float acPowerGrid, Float dcPowerAkku, Float acPowerLoad,
                                    Float autonomy, Float selfConsumption, Float stateOfChargeAkku, ZonedDateTime timestamp) {
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
        this.dcPowerAkku = dcPowerAkku;
        this.acPowerLoad = acPowerLoad;
        this.autonomy = autonomy;
        this.selfConsumption = selfConsumption;
        this.stateOfChargeAkku = stateOfChargeAkku;
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

    public Float getDcPowerAkku() {
        return dcPowerAkku;
    }

    public void setDcPowerAkku(Float dcPowerAkku) {
        this.dcPowerAkku = dcPowerAkku;
    }

    public Float getAcPowerLoad() {
        return acPowerLoad;
    }

    public void setAcPowerLoad(Float acPowerLoad) {
        this.acPowerLoad = acPowerLoad;
    }

    public Float getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(Float autonomy) {
        this.autonomy = autonomy;
    }

    public Float getSelfConsumption() {
        return selfConsumption;
    }

    public void setSelfConsumption(Float selfConsumption) {
        this.selfConsumption = selfConsumption;
    }

    public Float getStateOfChargeAkku() {
        return stateOfChargeAkku;
    }

    public void setStateOfChargeAkku(Float stateOfChargeAkku) {
        this.stateOfChargeAkku = stateOfChargeAkku;
    }

    @Override
    public String toString() {
        return "PowerFlowRealtimeDataDto{" +
                "dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", dcPowerAkku=" + dcPowerAkku +
                ", acPowerLoad=" + acPowerLoad +
                ", autonomy=" + autonomy +
                ", selfConsumption=" + selfConsumption +
                ", stateOfChargeAkku=" + stateOfChargeAkku +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");
        Map<String, Object> site = (Map<String, Object>) data.get("Site");
        Map<String, Object> inverters = (Map<String, Object>) data.get("Inverters");
        Map<String, Object> inverter1 = (Map<String, Object>) inverters.get("1");

        if (inverter1.get("SOC").getClass() == Integer.class) {
            this.stateOfChargeAkku = ((Integer) inverter1.get("SOC")).floatValue();
        } else if (inverter1.get("SOC").getClass() == Double.class) {
            this.stateOfChargeAkku = ((Double) inverter1.get("SOC")).floatValue();
        } else if (inverter1.get("SOC").getClass() == BigDecimal.class) {
            this.stateOfChargeAkku = ((BigDecimal) inverter1.get("SOC")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class PowerFlowRealtimeData: " + inverter1.get("SOC").getClass());
        }

        if (site.get("rel_Autonomy").getClass() == Integer.class) {
            this.autonomy = ((Integer) site.get("rel_Autonomy")).floatValue();
        } else if (site.get("rel_Autonomy").getClass() == Double.class) {
            this.autonomy = ((Double) site.get("rel_Autonomy")).floatValue();
        } else if (site.get("rel_Autonomy").getClass() == BigDecimal.class) {
            this.autonomy = ((BigDecimal) site.get("rel_Autonomy")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class PowerFlowRealtimeData: " + site.get("rel_Autonomy").getClass());
        }

        if (site.get("rel_SelfConsumption").getClass() == Integer.class) {
            this.selfConsumption = ((Integer) site.get("rel_SelfConsumption")).floatValue();
        } else if (site.get("rel_SelfConsumption").getClass() == Double.class) {
            this.selfConsumption = ((Double) site.get("rel_SelfConsumption")).floatValue();
        } else if (site.get("rel_SelfConsumption").getClass() == BigDecimal.class) {
            this.selfConsumption = ((BigDecimal) site.get("rel_SelfConsumption")).floatValue();
        }else {
            throw new IllegalStateException("Unexpected cast for class PowerFlowRealtimeData: " + site.get("rel_SelfConsumption").getClass());
        }

        if (site.get("P_PV").getClass() == Double.class) {
            this.dcPowerPv = ((Double) site.get("P_PV")).floatValue();
            this.acPowerGrid = ((Double) site.get("P_Grid")).floatValue();
            this.dcPowerAkku = ((Double) site.get("P_Akku")).floatValue();
            this.acPowerLoad = ((Double) site.get("P_Load")).floatValue();
        } else if (site.get("P_PV").getClass() == BigDecimal.class) {
            this.dcPowerPv = ((BigDecimal) site.get("P_PV")).floatValue();
            this.acPowerGrid = ((BigDecimal) site.get("P_Grid")).floatValue();
            this.dcPowerAkku = ((BigDecimal) site.get("P_Akku")).floatValue();
            this.acPowerLoad = ((BigDecimal) site.get("P_Load")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class PowerFlowRealtimeData: " + site.get("P_PV").getClass());
        }

    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }

}
