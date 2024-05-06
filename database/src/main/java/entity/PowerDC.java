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

    @Column(name = "dc_string_1", updatable = false, nullable = false)
    @Nonnull
    private Float dcPower1;

    public PowerDC() {
    }

    public PowerDC(Float dcPower1) {
        this.dcPower1 = dcPower1;
    }

    public Float getDcPower1() {
        return dcPower1;
    }

    public void setDcPower1(Float dcPower1) {
        this.dcPower1 = dcPower1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerDC dcPower1 = (PowerDC) o;
        return Objects.equals(dcPower1, dcPower1.dcPower1)
                && Objects.equals(super.getTimestamp(), dcPower1.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dcPower1, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "PowerDC{" +
                "dcPower1=" + dcPower1 +
                '}';
    }
}
