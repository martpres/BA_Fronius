package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponsePowerDcDto(Float dcPower1,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                   ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseCurrentAcDto{" +
                "acPhase1=" + dcPower1 +
                ", timestamp=" + timestamp +
                '}';
    }
}
