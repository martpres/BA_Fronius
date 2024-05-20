package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public class StorageRealtimeDataDto implements Serializable {
    private Float stateOfChargeStorage;
    private Integer capacityStorage;
    private String manufacturerStorage;
    private String modelStorage;
    private String serialStorage;
    private ZonedDateTime timestamp;

    public StorageRealtimeDataDto() {
    }

    public StorageRealtimeDataDto(Float stateOfChargeStorage, ZonedDateTime timestamp) {
        this.stateOfChargeStorage = stateOfChargeStorage;
        this.timestamp = timestamp;
    }

    public StorageRealtimeDataDto(Float stateOfChargeStorage, Integer capacityStorage, String manufacturerStorage,
                                  String modelStorage, String serialStorage, ZonedDateTime timestamp) {
        this.stateOfChargeStorage = stateOfChargeStorage;
        this.capacityStorage = capacityStorage;
        this.manufacturerStorage = manufacturerStorage;
        this.modelStorage = modelStorage;
        this.serialStorage = serialStorage;
        this.timestamp = timestamp;
    }

    public Float getStateOfChargeStorage() {
        return stateOfChargeStorage;
    }

    public void setStateOfChargeStorage(Float stateOfChargeStorage) {
        this.stateOfChargeStorage = stateOfChargeStorage;
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

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StorageRealtimeDataDto{" +
                "stateOfChargeStorage=" + stateOfChargeStorage +
                ", capacityStorage=" + capacityStorage +
                ", manufacturerStorage='" + manufacturerStorage + '\'' +
                ", modelStorage='" + modelStorage + '\'' +
                ", serialStorage='" + serialStorage + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @JsonProperty("Body")
    private void unpackData(Map<String, Object> body) {
        Map<String, Object> data = (Map<String, Object>) body.get("Data");
        Map<String, Object> controller = (Map<String, Object>) data.get("Controller");
        Map<String, Object> details = (Map<String, Object>) controller.get("Details");

        if (controller.get("StateOfCharge_Relative").getClass() == Double.class) {
            this.stateOfChargeStorage = ((Double) controller.get("StateOfCharge_Relative")).floatValue();
        } else if (controller.get("StateOfCharge_Relative").getClass() == BigDecimal.class) {
            this.stateOfChargeStorage = ((BigDecimal) controller.get("StateOfCharge_Relative")).floatValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class StorageRealtimeData");
        }

        if (controller.get("DesignedCapacity").getClass() == Integer.class) {
            this.capacityStorage = ((Integer) controller.get("DesignedCapacity")).intValue();
        } else {
            throw new IllegalStateException("Unexpected cast for class StorageRealtimeData");
        }

        this.manufacturerStorage = details.get("Manufacturer").toString();
        this.modelStorage = details.get("Model").toString();
        this.serialStorage = details.get("Serial").toString();
    }

    @JsonProperty("Head")
    private void unpackHead(Map<String, Object> head) {
        this.timestamp = ZonedDateTime.parse((String) head.get("Timestamp"));
    }

}
