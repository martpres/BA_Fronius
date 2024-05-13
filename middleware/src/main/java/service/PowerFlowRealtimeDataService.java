package service;

import dto.ResponsePowerFlowRealtimeDataDto;
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

    public QueryDslResponse<ResponsePowerFlowRealtimeDataDto> loadPowerFlowRealtimeData(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadPowerFlowRealtimeData(startDate,endDate,pageRequest);
    }

}
