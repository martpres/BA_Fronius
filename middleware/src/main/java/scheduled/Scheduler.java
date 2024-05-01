package scheduled;

import dto.CurrentAcDto;
import fronius.FroniusClientFactory;
import mapper.CurrentAcMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.CurrentAcRepository;

@Component
public class Scheduler {

    private final FroniusClientFactory froniusClientFactory;
    private final boolean currentAc;
    private final CurrentAcRepository currentAcRepository;
    private final CurrentAcMapper currentAcMapper;

    public Scheduler(@Value("${app.run.scheduled.current-ac:false}") boolean currentAc,
                     FroniusClientFactory froniusClientFactory,
                     CurrentAcRepository currentAcRepository,
                     CurrentAcMapper currentAcMapper) {
        this.froniusClientFactory = froniusClientFactory;
        this.currentAc = currentAc;
        this.currentAcRepository = currentAcRepository;
        this.currentAcMapper = currentAcMapper;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT20S")
    public void startWorkflowForCurrentAC(){
        if (currentAc) {
            CurrentAcDto dto = froniusClientFactory.getFroniusClient().currentAcEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            currentAcRepository.save(currentAcMapper.dtoToEntity(dto));
        }
    }
}
