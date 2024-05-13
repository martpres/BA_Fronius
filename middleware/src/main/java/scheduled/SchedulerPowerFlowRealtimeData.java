package scheduled;

import dto.PowerAcGridDto;
import dto.PowerFlowRealtimeDataDto;
import fronius.FroniusClientFactory;
import mapper.PowerAcGridMapper;
import mapper.PowerDcMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.PowerAcGridRepository;
import repository.PowerFlowRealtimeDataRepository;

@Component
public class SchedulerPowerFlowRealtimeData {

    private final boolean powerFlowRealtimeData;
//    private final boolean powerDc;
    private final PowerFlowRealtimeDataRepository powerFlowRealtimeDataRepository;
    private final PowerDcMapper powerDcMapper;
    private final FroniusClientFactory froniusClientFactory;
//    private final boolean powerAcGrid;
    private final PowerAcGridRepository powerAcGridRepository;
    private final PowerAcGridMapper powerAcGridMapper;

    public SchedulerPowerFlowRealtimeData(@Value("${power-flow-real-time-data:false}") boolean powerFlowRealtimeData1,
                                          PowerFlowRealtimeDataRepository powerFlowRealtimeDataRepository,
                                          PowerDcMapper powerDcMapper,
                                          FroniusClientFactory froniusClientFactory,
                                          PowerAcGridRepository powerAcGridRepository,
                                          PowerAcGridMapper powerAcGridMapper) {
        this.powerFlowRealtimeData = powerFlowRealtimeData1;
        this.powerFlowRealtimeDataRepository = powerFlowRealtimeDataRepository;
        this.powerDcMapper = powerDcMapper;
        this.froniusClientFactory = froniusClientFactory;
//        this.powerAcGrid = powerAcGrid;
        this.powerAcGridRepository = powerAcGridRepository;
        this.powerAcGridMapper = powerAcGridMapper;
    }


    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT25S")
    public void startWorkflowForPowerFlowRealtimeData(){
        if (powerFlowRealtimeData) {
            PowerFlowRealtimeDataDto dto = froniusClientFactory.getFroniusClient().powerFlowRealtimeDataEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            powerFlowRealtimeDataRepository.save(powerFlowRealtimeDataMapper.dtoToEntity(dto));
        }
    }
}
