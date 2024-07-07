package service;

import dto.ResponsePricesDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PricesQueryDslRepository;

import java.time.ZonedDateTime;

@Service
@Transactional(readOnly = true)
public class PricesService {
    private final PricesQueryDslRepository pricesQueryDslRepository;

    public PricesService(PricesQueryDslRepository pricesQueryDslRepository) {
        this.pricesQueryDslRepository = pricesQueryDslRepository;
    }

    public ResponsePricesDto loadLastPrice() {
        return pricesQueryDslRepository.getLastPrice();
    }

    public ResponsePricesDto loadPriceForDate(ZonedDateTime zonedDateTime) {
        return pricesQueryDslRepository.getPriceForDate(zonedDateTime);
    }

}
