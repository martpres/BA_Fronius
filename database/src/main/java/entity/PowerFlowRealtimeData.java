package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

// http://192.168.0.100/solar_api/v1/GetPowerFlowRealtimeData.fcgi
@Entity
@Table(name = "power_flow_realtime_data", catalog = "public")
public class PowerFlowRealtimeData extends AbstractBaseEntity{

    @Column(name = "dc_power_pv", updatable = false, nullable = false)
    @Nonnull
    private Float dcPowerPv;

    @Column(name = "ac_power_grid", updatable = false, nullable = false)
    @Nonnull
    private Float acPowerGrid;

    public PowerFlowRealtimeData() {
    }

    public PowerFlowRealtimeData(Float dcPowerPv, Float acPowerGrid) {
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerFlowRealtimeData powerFlowRealtimeData = (PowerFlowRealtimeData) o;
        return Objects.equals(dcPowerPv, powerFlowRealtimeData.dcPowerPv)
                && Objects.equals(acPowerGrid, powerFlowRealtimeData.acPowerGrid)
                && Objects.equals(super.getTimestamp(), powerFlowRealtimeData.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dcPowerPv, acPowerGrid, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "PowerFlowRealtimeData{" +
                "dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }
}
