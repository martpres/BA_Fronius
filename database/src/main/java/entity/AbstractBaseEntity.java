package entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Id
    @Column(name = "timestamp", updatable = false, nullable = false)
    private ZonedDateTime timestamp;

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
