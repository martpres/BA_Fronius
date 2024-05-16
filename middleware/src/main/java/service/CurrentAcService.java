package service;

import dto.ResponseCurrentAcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrentAcService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;

    public CurrentAcService(ParamsQueryDslRepository paramsQueryDslRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
    }

    public QueryDslResponse<ResponseCurrentAcDto> loadCurrentAc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadCurrentAc(startDate,endDate,pageRequest);
    }

}
