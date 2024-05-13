package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Objects;

// http://192.168.0.100/solar_api/v1/GetMeterRealtimeData.cgi?Scope=Device&DeviceId=0
@Entity
@Table(name = "params", catalog = "public")
public class ParamsEntity extends AbstractBaseEntity{

    @Column(name = "ac_phase_1", updatable = false)
    private Float acPhase1;

    @Column(name = "ac_phase_2", updatable = false)
    private Float acPhase2;

    @Column(name = "ac_phase_3", updatable = false)
    private Float acPhase3;

    @Column(name = "dc_power_pv", updatable = false)
    private Float dcPowerPv;

    @Column(name = "ac_power_grid", updatable = false)
    private Float acPowerGrid;

    public ParamsEntity() {
    }

    public ParamsEntity(Float dcPowerPv, Float acPowerGrid) {
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
    }

    public ParamsEntity(Float acPhase1, Float acPhase2, Float acPhase3) {
        this.acPhase1 = acPhase1;
        this.acPhase2 = acPhase2;
        this.acPhase3 = acPhase3;
    }

    public ParamsEntity(Float acPhase1, Float acPhase2, Float acPhase3, Float dcPowerPv, Float acPowerGrid) {
        this.acPhase1 = acPhase1;
        this.acPhase2 = acPhase2;
        this.acPhase3 = acPhase3;
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
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
        ParamsEntity paramsEntity = (ParamsEntity) o;
        return Objects.equals(acPhase1, paramsEntity.acPhase1)
                && Objects.equals(acPhase2, paramsEntity.acPhase2)
                && Objects.equals(acPhase3, paramsEntity.acPhase3)
                && Objects.equals(dcPowerPv, paramsEntity.dcPowerPv)
                && Objects.equals(acPowerGrid, paramsEntity.acPowerGrid)
                && Objects.equals(super.getTimestamp(), paramsEntity.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acPhase1, acPhase2, acPhase3, dcPowerPv, acPowerGrid, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "Params:{" +
                " acPhase1=" + acPhase1 +
                ", acPhase2=" + acPhase2 +
                ", acPhase3=" + acPhase3 +
                ", dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }

}
