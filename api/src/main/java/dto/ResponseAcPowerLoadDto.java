package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record ResponseAcPowerLoadDto(Float acPowerLoad,
                                     @JsonFormat(shape = JsonFormat.Shape.STRING,
                                             pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                     ZonedDateTime timestamp) implements Serializable {
    @Override
    public String toString() {
        return "ResponseAcPowerLoadDto{" +
                "AcPowerLoad=" + acPowerLoad +
                ", timestamp=" + timestamp +
                '}';
    }
}
