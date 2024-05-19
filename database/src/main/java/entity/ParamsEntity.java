package entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "params", catalog = "public")
public class ParamsEntity extends AbstractBaseEntity{

    @Column(name = "ac_current_phase1_grid", updatable = false)
    private Float acCurrentPhase1Grid;

    @Column(name = "ac_current_phase2_grid", updatable = false)
    private Float acCurrentPhase2Grid;

    @Column(name = "ac_current_phase3_grid", updatable = false)
    private Float acCurrentPhase3Grid;

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

    public ParamsEntity(Float acCurrentPhase1Grid, Float acCurrentPhase2Grid, Float acCurrentPhase3Grid) {
        this.acCurrentPhase1Grid = acCurrentPhase1Grid;
        this.acCurrentPhase2Grid = acCurrentPhase2Grid;
        this.acCurrentPhase3Grid = acCurrentPhase3Grid;
    }

    public ParamsEntity(Float acCurrentPhase1Grid, Float acCurrentPhase2Grid, Float acCurrentPhase3Grid, Float dcPowerPv, Float acPowerGrid) {
        this.acCurrentPhase1Grid = acCurrentPhase1Grid;
        this.acCurrentPhase2Grid = acCurrentPhase2Grid;
        this.acCurrentPhase3Grid = acCurrentPhase3Grid;
        this.dcPowerPv = dcPowerPv;
        this.acPowerGrid = acPowerGrid;
    }

    public Float getAcCurrentPhase1Grid() {
        return acCurrentPhase1Grid;
    }

    public void setAcCurrentPhase1Grid(Float acCurrentPhase1Grid) {
        this.acCurrentPhase1Grid = acCurrentPhase1Grid;
    }

    public Float getAcCurrentPhase2Grid() {
        return acCurrentPhase2Grid;
    }

    public void setAcCurrentPhase2Grid(Float acCurrentPhase2Grid) {
        this.acCurrentPhase2Grid = acCurrentPhase2Grid;
    }

    public Float getAcCurrentPhase3Grid() {
        return acCurrentPhase3Grid;
    }

    public void setAcCurrentPhase3Grid(Float acCurrentPhase3Grid) {
        this.acCurrentPhase3Grid = acCurrentPhase3Grid;
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
        return Objects.equals(acCurrentPhase1Grid, paramsEntity.acCurrentPhase1Grid)
                && Objects.equals(acCurrentPhase2Grid, paramsEntity.acCurrentPhase2Grid)
                && Objects.equals(acCurrentPhase3Grid, paramsEntity.acCurrentPhase3Grid)
                && Objects.equals(dcPowerPv, paramsEntity.dcPowerPv)
                && Objects.equals(acPowerGrid, paramsEntity.acPowerGrid)
                && Objects.equals(super.getTimestamp(), paramsEntity.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acCurrentPhase1Grid, acCurrentPhase2Grid, acCurrentPhase3Grid, dcPowerPv, acPowerGrid, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "Params:{" +
                " acCurrentPhase1Grid=" + acCurrentPhase1Grid +
                ", acCurrentPhase2Grid=" + acCurrentPhase2Grid +
                ", acCurrentPhase3Grid=" + acCurrentPhase3Grid +
                ", dcPowerPv=" + dcPowerPv +
                ", acPowerGrid=" + acPowerGrid +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }

}
