package service;

import dto.ResponseCurrentAcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CurrentAcQueryDslRepository;
import response.QueryDslResponse;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrentAcService {
    private final CurrentAcQueryDslRepository currentAcQueryDslRepository;

    public CurrentAcService(CurrentAcQueryDslRepository currentAcQueryDslRepository) {
        this.currentAcQueryDslRepository = currentAcQueryDslRepository;
    }

    public QueryDslResponse<ResponseCurrentAcDto> loadCurrentAc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return currentAcQueryDslRepository.loadCurrentAc(startDate,endDate,pageRequest);
    }

}
