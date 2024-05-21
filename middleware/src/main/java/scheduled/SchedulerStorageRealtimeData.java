package scheduled;

import dto.StorageRealtimeDataDto;
import fronius.FroniusClientFactory;
import mapper.StorageRealtimeDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.MetadataRepository;
import repository.ParamsRepository;

@Component
public class SchedulerStorageRealtimeData {

    private final boolean storageRealtimeData;
    private final ParamsRepository paramsRepository;
    private final MetadataRepository metadataRepository;
    private final StorageRealtimeDataMapper storageRealtimeDataMapper;
    private final FroniusClientFactory froniusClientFactory;

    public SchedulerStorageRealtimeData(@Value("${app.run.scheduled.common-inverter-data:false}")boolean storageRealtimeData1,
                                        ParamsRepository paramsRepository,
                                        MetadataRepository metadataRepository, StorageRealtimeDataMapper storageRealtimeDataMapper,
                                        FroniusClientFactory froniusClientFactory) {
        this.storageRealtimeData = storageRealtimeData1;
        this.paramsRepository = paramsRepository;
        this.metadataRepository = metadataRepository;
        this.storageRealtimeDataMapper = storageRealtimeDataMapper;
        this.froniusClientFactory = froniusClientFactory;
    }

    @Async
    @Scheduled(fixedDelayString = "PT20S", initialDelayString = "PT35S")
    public void startWorkflowForStorageRealtimeData() {
        if (storageRealtimeData) {
            StorageRealtimeDataDto dto = froniusClientFactory.getFroniusClient().storageRealtimeDataEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            paramsRepository.save(storageRealtimeDataMapper.dtoToEntityParams(dto));
            metadataRepository.save(storageRealtimeDataMapper.dtoToEntityMetadata(dto));
        }
    }
}
