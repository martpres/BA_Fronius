package scheduled;

import dto.CurrentAcDto;
import fronius.FroniusClientFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final FroniusClientFactory froniusClientFactory;

    public Scheduler(FroniusClientFactory froniusClientFactory) {
        this.froniusClientFactory = froniusClientFactory;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT10S")
    @ConditionalOnProperty(value = "app.run.scheduled.current-ac", matchIfMissing = true, havingValue = "false")
    public void startWorkflowForCurrentAC(){
        CurrentAcDto dto = froniusClientFactory.getFroniusClient().currentAcEndpoint();
    }


}
