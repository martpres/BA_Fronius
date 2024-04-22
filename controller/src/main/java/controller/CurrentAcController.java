package controller;

import dto.CurrentAcDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.CurrentAcService;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("api/current-ac")
@Transactional(readOnly = true)
public class CurrentAcController {
    private final CurrentAcService currentAcService;

    public CurrentAcController(CurrentAcService currentAcService) {
        this.currentAcService = currentAcService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public QueryDslResponse<CurrentAcDto> loadCurrentAc(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                        @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                        @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                        @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize ){
        return currentAcService.loadCurrentAc(startDate, endDate, PaginationUtil.getPagination(page,pagesize));
    }
}
