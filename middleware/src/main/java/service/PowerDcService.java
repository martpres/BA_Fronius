package service;

import dto.ResponseCurrentAcDto;
import dto.ResponsePowerDcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CurrentAcQueryDslRepository;
import repository.PowerDcQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerDcService {
    private final PowerDcQueryDslRepository powerDcQueryDslRepository;

    public PowerDcService(PowerDcQueryDslRepository powerDcQueryDslRepository) {
        this.powerDcQueryDslRepository = powerDcQueryDslRepository;
    }

    public QueryDslResponse<ResponsePowerDcDto> loadPowerDc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return powerDcQueryDslRepository.loadPowerDc(startDate,endDate,pageRequest);
    }

}
