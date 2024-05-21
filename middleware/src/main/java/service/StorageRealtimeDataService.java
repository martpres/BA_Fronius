package service;

import dto.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MetadataQueryDslRepository;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StorageRealtimeDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;
    private final MetadataQueryDslRepository metadataQueryDslRepository;

    public StorageRealtimeDataService(ParamsQueryDslRepository paramsQueryDslRepository, MetadataQueryDslRepository metadataQueryDslRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
        this.metadataQueryDslRepository = metadataQueryDslRepository;
    }

    public QueryDslResponse<ResponseStateOfChargeStorageDto> loadStateOfChargeStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return paramsQueryDslRepository.loadStateOfChargeStorage(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseCapacityStorageDto> loadCapacityStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return metadataQueryDslRepository.loadCapacityStorage(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseManufacturerStorageDto> loadManufacturerStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return metadataQueryDslRepository.loadManufacturerStorage(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseModelStorageDto> loadModelStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return metadataQueryDslRepository.loadModelStorage(startDate,endDate,pageRequest);
    }

    public QueryDslResponse<ResponseSerialStorageDto> loadSerialStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        return metadataQueryDslRepository.loadSerialStorage(startDate,endDate,pageRequest);
    }

}
