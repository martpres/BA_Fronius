package scheduled;

import dto.PowerFlowRealtimeDataDto;
import fronius.FroniusClientFactory;
import mapper.PowerFlowRealtimeDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.ParamsRepository;

@Component
public class SchedulerPowerFlowRealtimeData {

    private final boolean powerFlowRealtimeData;
    private final ParamsRepository paramsRepository;
    private final PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper;
    private final FroniusClientFactory froniusClientFactory;

    public SchedulerPowerFlowRealtimeData(@Value("${app.run.scheduled.power-flow-realtime-data:false}") boolean powerFlowRealtimeData1,
                                          ParamsRepository paramsRepository,
                                          PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper,
                                          FroniusClientFactory froniusClientFactory) {
        this.powerFlowRealtimeData = powerFlowRealtimeData1;
        this.paramsRepository = paramsRepository;
        this.powerFlowRealtimeDataMapper = powerFlowRealtimeDataMapper;
        this.froniusClientFactory = froniusClientFactory;
    }

    @Async
    @Scheduled(fixedDelayString = "PT30S", initialDelayString = "PT40S")
    public void startWorkflowForPowerFlowRealtimeData() {
        if (powerFlowRealtimeData) {
            PowerFlowRealtimeDataDto dto = froniusClientFactory.getFroniusClient().powerFlowRealtimeDataEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            paramsRepository.save(powerFlowRealtimeDataMapper.dtoToEntity(dto));
        }
    }
}
