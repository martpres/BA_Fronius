package controller;

import dto.ResponseAcPowerGridPhasesDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.MeterRealtimeDataService;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/meter-realtime-data")
@Transactional(readOnly = true)
public class MeterRealtimeDataController {
    private final MeterRealtimeDataService meterRealtimeDataService;

    public MeterRealtimeDataController(MeterRealtimeDataService meterRealtimeDataService) {
        this.meterRealtimeDataService = meterRealtimeDataService;
    }

    @GetMapping(value = "/ac-power-grid-phases", produces = "application/json")
    public QueryDslResponse<ResponseAcPowerGridPhasesDto> loadAcPowerGridPhases(
            @RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
            @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return meterRealtimeDataService.loadAcPowerGridPhases(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

}
