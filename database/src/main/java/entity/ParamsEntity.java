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

    @Column(name = "dc_power_akku", updatable = false)
    private Float dcPowerAkku;

    @Column(name = "ac_power_load", updatable = false)
    private Float acPowerLoad;

    @Column(name = "autonomy", updatable = false)
    private Float autonomy;

    @Column(name = "selfConsumption", updatable = false)
    private Float selfConsumption;

    @Column(name = "stateOfChargeAkku", updatable = false)
    private Float stateOfChargeAkku;

    @Column(name = "ac_power_inverter", updatable = false)
    private Float acPowerInverter;

    @Column(name = "dc_voltage_pv", updatable = false)
    private Float dcVoltagePv;

    @Column(name = "acEnergyInverterDay", updatable = false)
    private Float acEnergyInverterDay;

    @Column(name = "ac_power_grid_phase1", updatable = false)
    private Float acPowerGridPhase1;

    @Column(name = "ac_power_grid_phase2", updatable = false)
    private Float acPowerGridPhase2;

    @Column(name = "ac_power_grid_phase3", updatable = false)
    private Float acPowerGridPhase3;

    public ParamsEntity() {
    }

    public ParamsEntity(Float acCurrentGridPhase1, Float acCurrentGridPhase2, Float acCurrentGridPhase3, Float dcPowerPv,
                        Float acPowerGrid, Float acPowerInverter, Float dcVoltagePv, Float dcPowerAkku, Float acPowerLoad,
                        Float autonomy, Float selfConsumption, Float stateOfChargeAkku, Float acEnergyInverterDay,
                        Float acPowerGridPhase1, Float acPowerGridPhase2, Float acPowerGridPhase3) {
        this.acCurrentGridPhase1 = acCurrentGridPhase1;
        this.acCurrentGridPhase2 = acCurrentGridPhase2;
        this.acCurrentGridPhase3 = acCurrentGridPhase3;
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
        this.acPowerInverter = acPowerInverter;
        this.dcVoltagePv = dcVoltagePv;
        this.dcPowerAkku = dcPowerAkku;
        this.acPowerLoad = acPowerLoad;
        this.autonomy = autonomy;
        this.selfConsumption = selfConsumption;
        this.stateOfChargeAkku = stateOfChargeAkku;
        this.acEnergyInverterDay = acEnergyInverterDay;
        this.acPowerGridPhase1 = acPowerGridPhase1;
        this.acPowerGridPhase2 = acPowerGridPhase2;
        this.acPowerGridPhase3 = acPowerGridPhase3;
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
    public Float getAcEnergyInverterDay() {
        return acEnergyInverterDay;
    }
    public void setAcEnergyInverterDay(Float acEnergyInverterDay) {
        this.acEnergyInverterDay = acEnergyInverterDay;
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
                && Objects.equals(dcPowerAkku, paramsEntity.dcPowerAkku)
                && Objects.equals(acPowerLoad, paramsEntity.acPowerLoad)
                && Objects.equals(autonomy, paramsEntity.autonomy)
                && Objects.equals(selfConsumption, paramsEntity.selfConsumption)
                && Objects.equals(stateOfChargeAkku, paramsEntity.stateOfChargeAkku)
                && Objects.equals(acEnergyInverterDay, paramsEntity.acEnergyInverterDay)
                && Objects.equals(acPowerGridPhase1, paramsEntity.acPowerGridPhase1)
                && Objects.equals(acPowerGridPhase2, paramsEntity.acPowerGridPhase2)
                && Objects.equals(acPowerGridPhase3, paramsEntity.acPowerGridPhase3)
                && Objects.equals(super.getTimestamp(), paramsEntity.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acCurrentGridPhase1, acCurrentGridPhase2, acCurrentGridPhase3, dcPowerPv, acPowerGrid,
                dcVoltagePv, acPowerInverter, dcPowerAkku, acPowerLoad, autonomy, selfConsumption, stateOfChargeAkku,
                acEnergyInverterDay, acPowerGridPhase1, acPowerGridPhase3, acPowerGridPhase3, super.getTimestamp());
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
                ", dcPowerAkku=" + dcPowerAkku +
                ", acPowerLoad=" + acPowerLoad +
                ", autonomy=" + autonomy +
                ", selfConsumption=" + selfConsumption +
                ", stateOfChargeAkku=" + stateOfChargeAkku +
                ", acEnergyInverterDay=" + acEnergyInverterDay +
                "  acPowerGridPhase1=" + acPowerGridPhase1 +
                "  acPowerGridPhase2=" + acPowerGridPhase2 +
                "  acPowerGridPhase3=" + acPowerGridPhase3 +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }

}
