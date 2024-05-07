package controller;

import dto.ResponseCurrentAcDto;
import dto.ResponsePowerDcDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.CurrentAcService;
import service.PowerDcService;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/power-dc")
@Transactional(readOnly = true)
public class PowerDcController {
    private final PowerDcService powerDcService;

    public PowerDcController(PowerDcService powerDcService) {
        this.powerDcService = powerDcService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public QueryDslResponse<ResponsePowerDcDto> loadCurrentAc(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                              @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                              @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                              @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize ){
        return powerDcService.loadPowerDc(startDate, endDate, PaginationUtil.getPagination(page,pagesize));
    }
}
