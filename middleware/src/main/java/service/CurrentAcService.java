package service;

import dto.CurrentAcDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CurrentAcQueryDslRepository;
import response.QueryDslResponse;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CurrentAcService {
    private final CurrentAcQueryDslRepository currentAcQueryDslRepository;

    public CurrentAcService(CurrentAcQueryDslRepository currentAcQueryDslRepository) {
        this.currentAcQueryDslRepository = currentAcQueryDslRepository;
    }

    public QueryDslResponse<CurrentAcDto> loadCurrentAc(Optional<LocalDate> startDate, Optional<LocalDate> endDate, Optional<PageRequest> pageRequest){
        return currentAcQueryDslRepository.loadCurrentAc(startDate,endDate,pageRequest);
    }

}
