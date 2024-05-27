package service;

import dto.ResponseAcEnergyInverterDayDto;
import dto.ResponseAcPowerInverterDto;
import dto.ResponseDcVoltagePvDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommonInverterDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;

    public CommonInverterDataService(ParamsQueryDslRepository paramsQueryDslRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
    }

    public QueryDslResponse<ResponseDcVoltagePvDto> loadDcVoltagePv(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadDcVoltagePv(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseAcPowerInverterDto> loadAcPowerInverter(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadAcPowerInverter(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseAcEnergyInverterDayDto> loadAcEnergyInverterDay(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadAcEnergyInverterDay(startDate,endDate,pageRequest);
    }

}
