package scheduled;

import dto.MeterRealtimeDataDto;
import fronius.FroniusClientFactory;
import mapper.MeterRealtimeDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.ParamsRepository;

@Component
public class SchedulerMeterRealtimeData {

    private final boolean meterRealtimeData;
    private final ParamsRepository paramsRepository;
    private final MeterRealtimeDataMapper meterRealtimeDataMapper;
    private final FroniusClientFactory froniusClientFactory;

    public SchedulerMeterRealtimeData(@Value("${app.run.scheduled.meter-realtime-data:false}") boolean meterRealtimeData,
                                      FroniusClientFactory froniusClientFactory,
                                      ParamsRepository paramsRepository,
                                      MeterRealtimeDataMapper meterRealtimeDataMapper) {
        this.froniusClientFactory = froniusClientFactory;
        this.meterRealtimeData = meterRealtimeData;
        this.paramsRepository = paramsRepository;
        this.meterRealtimeDataMapper = meterRealtimeDataMapper;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT20S")
    public void startWorkflowForMeterRealtimeData(){
        if (meterRealtimeData) {
            MeterRealtimeDataDto dto = froniusClientFactory.getFroniusClient().meterRealtimeDataEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            paramsRepository.save(meterRealtimeDataMapper.dtoToEntity(dto));
        }
    }
}
