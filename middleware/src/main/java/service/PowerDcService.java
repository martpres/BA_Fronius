package service;

import dto.ResponsePowerDcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PowerFlowRealtimeDataQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerDcService {
    private final PowerFlowRealtimeDataQueryDslRepository powerFlowRealtimeDataQueryDslRepository;

    public PowerDcService(PowerFlowRealtimeDataQueryDslRepository powerFlowRealtimeDataQueryDslRepository) {
        this.powerFlowRealtimeDataQueryDslRepository = powerFlowRealtimeDataQueryDslRepository;
    }

    public QueryDslResponse<ResponsePowerDcDto> loadPowerDc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return powerFlowRealtimeDataQueryDslRepository.loadPowerDc(startDate,endDate,pageRequest);
    }

}
