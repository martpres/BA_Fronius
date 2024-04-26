package entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Id
    @Column(name = "primary_timestamp", updatable = false, nullable = false, columnDefinition = "timestamp with time zone")
    private ZonedDateTime timestamp;

    public ZonedDateTime getTimestamp() {
        return timestamp.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp.withZoneSameInstant(ZoneId.of("UTC"));
    }
}
