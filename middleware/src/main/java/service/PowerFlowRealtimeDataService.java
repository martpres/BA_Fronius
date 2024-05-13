package service;

import dto.ResponsePowerFlowRealtimeDataDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PowerFlowRealtimeDataQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerFlowRealtimeDataService {
    private final PowerFlowRealtimeDataQueryDslRepository powerFlowRealtimeDataQueryDslRepository;

    public PowerFlowRealtimeDataService(PowerFlowRealtimeDataQueryDslRepository powerFlowRealtimeDataQueryDslRepository) {
        this.powerFlowRealtimeDataQueryDslRepository = powerFlowRealtimeDataQueryDslRepository;
    }

    public QueryDslResponse<ResponsePowerFlowRealtimeDataDto> loadPowerFlowRealtimeData(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return powerFlowRealtimeDataQueryDslRepository.loadPowerFlowRealtimeData(startDate,endDate,pageRequest);
    }

}
