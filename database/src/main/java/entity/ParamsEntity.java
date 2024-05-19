package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "params", catalog = "public")
public class ParamsEntity extends AbstractBaseEntity {

    @Column(name = "ac_current_grid_phase1", updatable = false)
    private Float acCurrentGridPhase1;

    @Column(name = "ac_current_grid_phase2", updatable = false)
    private Float acCurrentGridPhase2;

    @Column(name = "ac_current_grid_phase3", updatable = false)
    private Float acCurrentGridPhase3;

    @Column(name = "dc_power_pv", updatable = false)
    private Float dcPowerPv;

    @Column(name = "ac_power_grid", updatable = false)
    private Float acPowerGrid;

    @Column(name = "ac_power_inverter", updatable = false)
    private Float acPowerInverter;

    @Column(name = "dc_voltage_pv", updatable = false)
    private Float dcVoltagePv;

    public ParamsEntity() {
    }

    public ParamsEntity(Float acCurrentGridPhase1, Float acCurrentGridPhase2, Float acCurrentGridPhase3, Float dcPowerPv, Float acPowerGrid, Float acPowerInverter, Float dcVoltagePv) {
        this.acCurrentGridPhase1 = acCurrentGridPhase1;
        this.acCurrentGridPhase2 = acCurrentGridPhase2;
        this.acCurrentGridPhase3 = acCurrentGridPhase3;
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
        this.acPowerInverter = acPowerInverter;
        this.dcVoltagePv = dcVoltagePv;
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

    public Float getAcPowerInverter() {
        return acPowerInverter;
    }

    public void setAcPowerInverter(Float acPowerInverter) {
        this.acPowerInverter = acPowerInverter;
    }

    public Float getDcVoltagePv() {
        return dcVoltagePv;
    }

    public void setDcVoltagePv(Float dcVoltagePv) {
        this.dcVoltagePv = dcVoltagePv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamsEntity paramsEntity = (ParamsEntity) o;
        return Objects.equals(acCurrentGridPhase1, paramsEntity.acCurrentGridPhase1)
                && Objects.equals(acCurrentGridPhase2, paramsEntity.acCurrentGridPhase2)
                && Objects.equals(acCurrentGridPhase3, paramsEntity.acCurrentGridPhase3)
                && Objects.equals(dcPowerPv, paramsEntity.dcPowerPv)
                && Objects.equals(acPowerGrid, paramsEntity.acPowerGrid)
                && Objects.equals(dcVoltagePv, paramsEntity.dcVoltagePv)
                && Objects.equals(acPowerInverter, paramsEntity.acPowerInverter)
                && Objects.equals(super.getTimestamp(), paramsEntity.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acCurrentGridPhase1, acCurrentGridPhase2, acCurrentGridPhase3,
                dcPowerPv, acPowerGrid, dcVoltagePv, acPowerInverter, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "Params:{" +
                "  acCurrentGridPhase1=" + acCurrentGridPhase1 +
                ", acCurrentGridPhase2=" + acCurrentGridPhase2 +
                ", acCurrentGridPhase3=" + acCurrentGridPhase3 +
                ", dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", dcVoltagePv=" + dcVoltagePv +
                ", acPowerInverter=" + acPowerInverter +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }

}
