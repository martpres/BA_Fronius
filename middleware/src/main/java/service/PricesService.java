package service;

import dto.ResponsePricesDto;
import entity.PricesEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PricesQueryDslRepository;
import repository.PricesRepository;

import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
public class PricesService {
    private final PricesQueryDslRepository pricesQueryDslRepository;
    private final PricesRepository pricesRepository;

    public PricesService(PricesQueryDslRepository pricesQueryDslRepository, PricesRepository pricesRepository) {
        this.pricesQueryDslRepository = pricesQueryDslRepository;
        this.pricesRepository = pricesRepository;
    }

    public ResponsePricesDto loadLastPrice() {
        return pricesQueryDslRepository.getLastPrice();
    }

    public ResponsePricesDto loadPriceForDate(ZonedDateTime zonedDateTime) {
        return pricesQueryDslRepository.getPriceForDate(zonedDateTime);
    }

    @Transactional
    public void updatePrice(ResponsePricesDto responsePricesDto) {
        PricesEntity oldPrice = pricesQueryDslRepository.getLastPriceEntity();
        ZonedDateTime currentDate = ZonedDateTime.now();

        if (oldPrice != null) {
            oldPrice.setEndDay(currentDate);
            pricesRepository.save(oldPrice);
        }

        PricesEntity newPrice = new PricesEntity();
        newPrice.setBeginDay(currentDate.plusSeconds(1));
        newPrice.setKwhPriceFromGrid(responsePricesDto.kwhPriceFromGrid());
        newPrice.setKwhPriceIntoGrid(responsePricesDto.kwhPriceIntoGrid());
        pricesRepository.save(newPrice);
    }

}
