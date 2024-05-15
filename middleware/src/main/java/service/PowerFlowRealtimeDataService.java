package service;

import dto.ResponseAcPowerGridDto;
import dto.ResponseDcPowerPvDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerFlowRealtimeDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;

    public PowerFlowRealtimeDataService(ParamsQueryDslRepository paramsQueryDslRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
    }

    public QueryDslResponse<ResponseDcPowerPvDto> loadDcPowerPv(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadDcPowerPv(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseAcPowerGridDto> loadAcPowerGrid(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadAcPowerGrid(startDate,endDate,pageRequest);
    }

}
