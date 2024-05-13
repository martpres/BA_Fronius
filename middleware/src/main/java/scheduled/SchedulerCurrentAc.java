package scheduled;

import dto.CurrentAcDto;
import fronius.FroniusClientFactory;
import mapper.CurrentAcMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.ParamsRepository;

@Component
public class SchedulerCurrentAc {

    private final FroniusClientFactory froniusClientFactory;
    private final boolean currentAc;
    private final ParamsRepository paramsRepository;
    private final CurrentAcMapper currentAcMapper;

    public SchedulerCurrentAc(@Value("${app.run.scheduled.current-ac:false}") boolean currentAc,
                              FroniusClientFactory froniusClientFactory,
                              ParamsRepository paramsRepository,
                              CurrentAcMapper currentAcMapper) {
        this.froniusClientFactory = froniusClientFactory;
        this.currentAc = currentAc;
        this.paramsRepository = paramsRepository;
        this.currentAcMapper = currentAcMapper;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT20S")
    public void startWorkflowForCurrentAC(){
        if (currentAc) {
            CurrentAcDto dto = froniusClientFactory.getFroniusClient().currentAcEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            paramsRepository.save(currentAcMapper.dtoToEntity(dto));
        }
    }
}
