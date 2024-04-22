package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Objects;

// http://192.168.0.100/solar_api/v1/GetMeterRealtimeData.cgi?Scope=Device&DeviceId=0
@Entity
@Table(name = "current_ac", catalog = "public")
public class CurrentAC extends AbstractBaseEntity{

    @Column(name = "ac_phase_1", updatable = false, nullable = false, columnDefinition = "numeric")
    @Nonnull
    private Float acPhase1;

    @Column(name = "ac_phase_2", updatable = false, nullable = false, columnDefinition = "numeric")
    @Nonnull
    private Float acPhase2;

    @Column(name = "ac_phase_3", updatable = false, nullable = false, columnDefinition = "numeric")
    @Nonnull
    private Float acPhase3;

    public CurrentAC() {
    }

    public CurrentAC(Float acPhase1, Float acPhase2, Float acPhase3) {
        this.acPhase1 = acPhase1;
        this.acPhase2 = acPhase2;
        this.acPhase3 = acPhase3;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentAC currentAC = (CurrentAC) o;
        return Objects.equals(acPhase1, currentAC.acPhase1)
                && Objects.equals(acPhase2, currentAC.acPhase2)
                && Objects.equals(acPhase3, currentAC.acPhase3)
                && Objects.equals(super.getTimestamp(), currentAC.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(acPhase1, acPhase2, acPhase3, super.getTimestamp());
    }

    @Override
    public String toString() {
        return "CurrentAC{" +
                "acPhase1=" + acPhase1 +
                ", acPhase2=" + acPhase2 +
                ", acPhase3=" + acPhase3 +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }

}
