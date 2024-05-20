package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "metadata", catalog = "public")
public class MetadataEntity extends AbstractBaseEntity {

    @Column(name = "capacity_storage", updatable = false)
    private Integer capacityStorage;

    @Column(name = "manufacturer_storage", updatable = false)
    private String manufacturerStorage;

    @Column(name = "model_storage", updatable = false)
    private String modelStorage;

    @Column(name = "serial_storage", updatable = false)
    private String serialStorage;

    public MetadataEntity() {
    }

    public MetadataEntity(Integer capacityStorage, String manufacturerStorage, String modelStorage, String serialStorage) {
        this.capacityStorage = capacityStorage;
        this.manufacturerStorage = manufacturerStorage;
        this.modelStorage = modelStorage;
        this.serialStorage = serialStorage;
    }

    public Integer getCapacityStorage() {
        return capacityStorage;
    }

    public void setCapacityStorage(Integer capacityStorage) {
        this.capacityStorage = capacityStorage;
    }

    public String getManufacturerStorage() {
        return manufacturerStorage;
    }

    public void setManufacturerStorage(String manufacturerStorage) {
        this.manufacturerStorage = manufacturerStorage;
    }

    public String getModelStorage() {
        return modelStorage;
    }

    public void setModelStorage(String modelStorage) {
        this.modelStorage = modelStorage;
    }

    public String getSerialStorage() {
        return serialStorage;
    }

    public void setSerialStorage(String serialStorage) {
        this.serialStorage = serialStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetadataEntity metadataEntity = (MetadataEntity) o;
        return Objects.equals(capacityStorage, metadataEntity.capacityStorage)
                && Objects.equals(manufacturerStorage, metadataEntity.manufacturerStorage)
                && Objects.equals(modelStorage, metadataEntity.modelStorage)
                && Objects.equals(serialStorage, metadataEntity.serialStorage)
                && Objects.equals(super.getTimestamp(), metadataEntity.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacityStorage, manufacturerStorage, modelStorage,
                serialStorage, super.getTimestamp());
    }


    @Override
    public String toString() {
        return "MetadataEntity{" +
                "capacityStorage=" + capacityStorage +
                ", manufacturerStorage=" + manufacturerStorage +
                ", modelStorage=" + modelStorage +
                ", serialStorage=" + serialStorage +
                ", timestamp=" + super.getTimestamp() +
                '}';
    }
}
