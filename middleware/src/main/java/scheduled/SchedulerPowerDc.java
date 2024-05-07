package scheduled;

import dto.CurrentAcDto;
import dto.PowerDcDto;
import fronius.FroniusClientFactory;
import mapper.CurrentAcMapper;
import mapper.PowerDcMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.CurrentAcRepository;
import repository.PowerDcRepository;

@Component
public class SchedulerPowerDc {

    private final FroniusClientFactory froniusClientFactory;
    private final boolean powerDc;
    private final PowerDcRepository powerDcRepository;
    private final PowerDcMapper powerDcMapper;

    public SchedulerPowerDc(@Value("${app.run.scheduled.power-dc:false}") boolean powerDc,
                            FroniusClientFactory froniusClientFactory,
                            PowerDcRepository powerDcRepository,
                            PowerDcMapper powerDcMapper) {
        this.froniusClientFactory = froniusClientFactory;
        this.powerDc = powerDc;
        this.powerDcRepository = powerDcRepository;
        this.powerDcMapper = powerDcMapper;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT10S")
    public void startWorkflowForPowerDC(){
        if (powerDc) {
            PowerDcDto dto = froniusClientFactory.getFroniusClient().powerDcEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            powerDcRepository.save(powerDcMapper.dtoToEntity(dto));
        }
    }
}
