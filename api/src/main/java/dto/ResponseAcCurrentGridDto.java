package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponseAcCurrentGridDto(Float acCurrentGridPhase1,
                                       Float acCurrentGridPhase2,
                                       Float acCurrentGridPhase3,
                                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                       ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseAcCurrentGridDto{" +
                "acCurrentGridPhase1=" + acCurrentGridPhase1 +
                ", acCurrentGridPhase2=" + acCurrentGridPhase1 +
                ", acCurrentGridPhase3=" + acCurrentGridPhase1 +
                ", timestamp=" + timestamp +
                '}';
    }
}
