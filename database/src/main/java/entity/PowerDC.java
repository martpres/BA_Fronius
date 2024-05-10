package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

// http://192.168.0.100/solar_api/v1/GetPowerFlowRealtimeData.fcgi
@Entity
@Table(name = "power_dc", catalog = "public")
public class PowerDC extends AbstractBaseEntity{

    @Column(name = "dc_power_pv", updatable = false, nullable = false)
    @Nonnull
    private Float dcPowerPv;

    public PowerDC() {
    }

    public PowerDC(Float dcPowerPv) {
        this.dcPowerPv = dcPowerPv;
    }

    public Float getDcPowerPv() {
        return dcPowerPv;
    }

    public void setDcPowerPv(Float dcPowerPv) {
        this.dcPowerPv = dcPowerPv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerDC dcPowerPv1 = (PowerDC) o;
        return Objects.equals(dcPowerPv, dcPowerPv1.dcPowerPv)
                && Objects.equals(super.getTimestamp(), dcPowerPv1.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dcPowerPv, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "PowerDC{" +
                "dcPowerPv=" + dcPowerPv +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }
}
