package dto;

import java.io.Serializable;

public record ResponseEnergyDayDto(Double energyDay) implements Serializable {
    @Override
    public String toString() {
        return "ResponseEnergyDayDto{" +
                "EnergyDay=" + energyDay +
                '}';
    }
}
