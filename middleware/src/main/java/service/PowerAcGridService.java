package service;

import dto.ResponsePowerAcGridDto;
import dto.ResponsePowerDcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PowerAcGridQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerAcGridService {
    private final PowerAcGridQueryDslRepository powerAcGridQueryDslRepository;

    public PowerAcGridService(PowerAcGridQueryDslRepository powerAcGridQueryDslRepository) {
        this.powerAcGridQueryDslRepository = powerAcGridQueryDslRepository;
    }

    public QueryDslResponse<ResponsePowerAcGridDto> loadPowerAcGrid(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return powerAcGridQueryDslRepository.loadPowerAcGrid(startDate,endDate,pageRequest);
    }

}
