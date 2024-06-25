package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponseAcPowerGridPhasesDto(Float acPowerGridPhase1,
                                           Float acPowerGridPhase2,
                                           Float acPowerGridPhase3,
                                           @JsonFormat(shape = JsonFormat.Shape.STRING,
                                                   pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                           ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseAcPowerGridPhasesDto{" +
                "acPowerGridPhase1=" + acPowerGridPhase1 +
                ", acPowerGridPhase2=" + acPowerGridPhase2 +
                ", acPowerGridPhase3=" + acPowerGridPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }
}
