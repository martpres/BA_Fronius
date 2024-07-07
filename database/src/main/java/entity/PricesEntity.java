package entity;

import jakarta.persistence.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "prices", catalog = "public")
public class PricesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "kwh_price_from_grid")
    private Float kwhPriceFromGrid;

    @Column(name = "kwh_price_into_grid")
    private Float kwhPriceIntoGrid;

    @Column(name = "begin_day")
    private ZonedDateTime beginDay;

    @Column(name = "end_day")
    private ZonedDateTime endDay;

    public PricesEntity() {
    }

    public PricesEntity(Integer id, Float kwhPriceFromGrid, Float kwhPriceIntoGrid, ZonedDateTime beginDay, ZonedDateTime endDay) {
        this.id = id;
        this.kwhPriceFromGrid = kwhPriceFromGrid;
        this.kwhPriceIntoGrid = kwhPriceIntoGrid;
        this.beginDay = beginDay;
        this.endDay = endDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getKwhPriceFromGrid() {
        return kwhPriceFromGrid;
    }

    public void setKwhPriceFromGrid(Float kwhPriceFromGrid) {
        this.kwhPriceFromGrid = kwhPriceFromGrid;
    }

    public Float getKwhPriceIntoGrid() {
        return kwhPriceIntoGrid;
    }

    public void setKwhPriceIntoGrid(Float kwhPriceIntoGrid) {
        this.kwhPriceIntoGrid = kwhPriceIntoGrid;
    }

    public ZonedDateTime getBeginDay() {
        return beginDay.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setBeginDay(ZonedDateTime beginDay) {
        this.beginDay = beginDay.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public ZonedDateTime getEndDay() {
        return endDay.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public void setEndDay(ZonedDateTime endDay) {
        this.endDay = endDay.withZoneSameInstant(ZoneId.of("UTC"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricesEntity pricesEntity = (PricesEntity) o;
        return Objects.equals(id, pricesEntity.id)
                && Objects.equals(kwhPriceFromGrid, pricesEntity.kwhPriceFromGrid)
                && Objects.equals(kwhPriceIntoGrid, pricesEntity.kwhPriceIntoGrid)
                && Objects.equals(beginDay, pricesEntity.beginDay)
                && Objects.equals(endDay, pricesEntity.endDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kwhPriceFromGrid, kwhPriceIntoGrid, beginDay, endDay);
    }

    @Override
    public String toString() {
        return "PricesEntity{" +
                "id=" + id +
                ", kwhPriceFromGrid=" + kwhPriceFromGrid +
                ", kwhPriceIntoGrid=" + kwhPriceIntoGrid +
                ", beginDay=" + beginDay +
                ", endDay=" + endDay +
                '}';
    }
}
