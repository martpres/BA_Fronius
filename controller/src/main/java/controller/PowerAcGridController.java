package controller;

import dto.ResponsePowerAcGridDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.PowerAcGridService;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/power-ac-grid")
@Transactional(readOnly = true)
public class PowerAcGridController {
    private final PowerAcGridService powerAcGridService;

    public PowerAcGridController(PowerAcGridService powerAcGridService) {
        this.powerAcGridService = powerAcGridService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public QueryDslResponse<ResponsePowerAcGridDto> loadPowerAcGrid(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize ){
        return powerAcGridService.loadPowerAcGrid(startDate, endDate, PaginationUtil.getPagination(page,pagesize));
//        return powerAcGridService.loadPowerAcGrid(startDate, endDate, PaginationUtil.getPagination(page,pagesize));
    }
}
