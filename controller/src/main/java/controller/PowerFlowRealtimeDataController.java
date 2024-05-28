package controller;

import dto.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.PowerFlowRealtimeDataService;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/power-flow-realtime-data")
@Transactional(readOnly = true)
public class PowerFlowRealtimeDataController {
    private final PowerFlowRealtimeDataService powerFlowRealtimeDataService;

    public PowerFlowRealtimeDataController(PowerFlowRealtimeDataService powerFlowRealtimeDataService) {
        this.powerFlowRealtimeDataService = powerFlowRealtimeDataService;
    }

    @GetMapping(value = "/dc-power-pv", produces = "application/json")
    public QueryDslResponse<ResponseDcPowerPvDto> loadDcPowerPv(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadDcPowerPv(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/ac-power-grid", produces = "application/json")
    public QueryDslResponse<ResponseAcPowerGridDto> loadAcPowerGrid(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadAcPowerGrid(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/dc-power-akku", produces = "application/json")
    public QueryDslResponse<ResponseDcPowerAkkuDto> loadDcPowerAkku(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadDcPowerAkku(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/ac-power-load", produces = "application/json")
    public QueryDslResponse<ResponseAcPowerLoadDto> loadAcPowerLoad(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadAcPowerLoad(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/autonomy", produces = "application/json")
    public QueryDslResponse<ResponseAutonomyDto> loadAutonomy(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                              @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                              @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                              @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadAutonomy(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/self-consumption", produces = "application/json")
    public QueryDslResponse<ResponseSelfConsumptionDto> loadSelfConsumption(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                            @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                            @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                            @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadSelfConsumption(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/state-of-charge-akku", produces = "application/json")
    public QueryDslResponse<ResponseStateOfChargeAkkuDto> loadStateOfChargeAkku(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return powerFlowRealtimeDataService.loadStateOfChargeAkku(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

}
