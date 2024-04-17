package controller;

import dto.CurrentAcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import response.QueryDslResponse;
import service.CurrentAcService;

import java.time.LocalDate;
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
    public QueryDslResponse<CurrentAcDto> loadCurrentAc(Optional<LocalDate> startDate, Optional<LocalDate> endDate, Optional<Integer> page, Optional<Integer> pagesize ){
        return currentAcService.loadCurrentAc(startDate, endDate,  )
    }
}
