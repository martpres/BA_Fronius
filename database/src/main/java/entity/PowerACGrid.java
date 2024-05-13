package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

// http://192.168.0.100/solar_api/v1/GetPowerFlowRealtimeData.fcgi
@Entity
@Table(name = "power_ac_grid", catalog = "public")
public class PowerACGrid extends AbstractBaseEntity{

    @Column(name = "ac_power_grid", updatable = false, nullable = false)
    @Nonnull
    private Float acPowerGrid;

    public PowerACGrid() {
    }

    public PowerACGrid(Float acPowerGrid) {
        this.acPowerGrid = acPowerGrid;
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
        PowerACGrid acPowerGrid = (PowerACGrid) o;
        return Objects.equals(acPowerGrid, acPowerGrid.acPowerGrid)
                && Objects.equals(super.getTimestamp(), acPowerGrid.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acPowerGrid, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "PowerACGrid{" +
                "acPowerGrid=" + acPowerGrid +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }
}
