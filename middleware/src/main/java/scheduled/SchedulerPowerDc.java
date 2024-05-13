package scheduled;

import dto.PowerDcDto;
import fronius.FroniusClientFactory;
import mapper.PowerDcMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.PowerFlowRealtimeDataRepository;

@Component
public class SchedulerPowerDc {

    private final FroniusClientFactory froniusClientFactory;
    private final boolean powerDc;
    private final PowerFlowRealtimeDataRepository powerFlowRealtimeDataRepository;
    private final PowerDcMapper powerDcMapper;

    public SchedulerPowerDc(@Value("${app.run.scheduled.power-dc:false}") boolean powerDc,
                            FroniusClientFactory froniusClientFactory,
                            PowerFlowRealtimeDataRepository powerFlowRealtimeDataRepository,
                            PowerDcMapper powerDcMapper) {
        this.froniusClientFactory = froniusClientFactory;
        this.powerDc = powerDc;
        this.powerFlowRealtimeDataRepository = powerFlowRealtimeDataRepository;
        this.powerDcMapper = powerDcMapper;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT10S")
    public void startWorkflowForPowerDC(){
        if (powerDc) {
            PowerDcDto dto = froniusClientFactory.getFroniusClient().powerDcEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            powerFlowRealtimeDataRepository.save(powerDcMapper.dtoToEntity(dto));
        }
    }
}
