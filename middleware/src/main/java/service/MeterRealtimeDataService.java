package service;

import dto.ResponseAcPowerGridPhasesDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeterRealtimeDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;

    public MeterRealtimeDataService(ParamsQueryDslRepository paramsQueryDslRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
    }

    public QueryDslResponse<ResponseAcPowerGridPhasesDto> loadAcPowerGridPhases(Optional<ZonedDateTime> startDate,
                                                                                Optional<ZonedDateTime> endDate,
                                                                                Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadAcPowerGridPhases(startDate, endDate, pageRequest);
    }

}
