package entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

// http://192.168.0.100/solar_api/v1/GetMeterRealtimeData.cgi?Scope=Device&DeviceId=0

@Entity
@Table(name = "current_AC")
public class CurrentAC extends AbstractBaseEntity{

    @Column(name = "ac_phase_1", updatable = false, nullable = false)
    @Nonnull
    private Float acPhase1;

    @Column(name = "ac_phase_2", updatable = false, nullable = false)
    @Nonnull
    private Float acPhase2;

    @Column(name = "ac_phase_3", updatable = false, nullable = false)
    @Nonnull
    private Float acPhase3;



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
}
