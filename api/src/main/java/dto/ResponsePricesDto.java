package dto;

import java.io.Serializable;

public record ResponsePricesDto(Float kwhPriceFromGrid, Float kwhPriceIntoGrid) implements Serializable {

    @Override
    public String toString() {
        return "ResponsePricesDto{" +
                "kwhPriceFromGrid=" + kwhPriceFromGrid +
                ", kwhPriceIntoGrid=" + kwhPriceIntoGrid +
                '}';
    }
}
