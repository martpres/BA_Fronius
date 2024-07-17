package controller;

import dto.ResponsePricesDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import service.PricesService;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/prices")
@Transactional(readOnly = false)
public class PricesController {
    private final PricesService pricesService;

    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(value = "/archiv", produces = "application/json")
    public ResponsePricesDto loadPriceForDate(
            @RequestParam(value = "date") ZonedDateTime zonedDateTime) {
        return pricesService.loadPriceForDate(zonedDateTime);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponsePricesDto loadLastPrice() {
        return pricesService.loadLastPrice();
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public void updatePrice(@RequestBody ResponsePricesDto responsePricesDto) {
        pricesService.updatePrice(responsePricesDto);
    }

}
