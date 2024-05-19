package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponseAcCurrentGridDto(Float acCurrentPhase1Grid,
                                       Float acCurrentPhase2Grid,
                                       Float acCurrentPhase3Grid,
                                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                   ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseAcCurrentGridDto{" +
                "acCurrentPhase1Grid=" + acCurrentPhase1Grid +
                ", acCurrentPhase2Grid=" + acCurrentPhase2Grid +
                ", acCurrentPhase3Grid=" + acCurrentPhase3Grid +
                ", timestamp=" + timestamp +
                '}';
    }
}
