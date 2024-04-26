package dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponseCurrentAcDto(Float acPhase1, Float acPhase2, Float acPhase3, ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseCurrentAcDto{" +
                "acPhase1=" + acPhase1 +
                ", acPhase2=" + acPhase2 +
                ", acPhase3=" + acPhase3 +
                ", timestamp=" + timestamp +
                '}';
    }
}
