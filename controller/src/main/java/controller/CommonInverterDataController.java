package controller;

import dto.ResponseAcEnergyInverterDayDto;
import dto.ResponseAcPowerInverterDto;
import dto.ResponseDcVoltagePvDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.CommonInverterDataService;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/common-inverter-data")
@Transactional(readOnly = true)
public class CommonInverterDataController {
    private final CommonInverterDataService commonInverterDataService;

    public CommonInverterDataController(CommonInverterDataService commonInverterDataService) {
        this.commonInverterDataService = commonInverterDataService;
    }

    @GetMapping(value = "/ac-power-inverter", produces = "application/json")
    public QueryDslResponse<ResponseAcPowerInverterDto> loadAcPowerInverter(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                            @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                            @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                            @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return commonInverterDataService.loadAcPowerInverter(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/dc-voltage-pv", produces = "application/json")
    public QueryDslResponse<ResponseDcVoltagePvDto> loadDcVoltagePv(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return commonInverterDataService.loadDcVoltagePv(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/ac-energy-inverter-day/latest", produces = "application/json")
    public ResponseAcEnergyInverterDayDto loadAcEnergyInverterDay() {
        return commonInverterDataService.loadAcEnergyInverterDay();
    }
}
