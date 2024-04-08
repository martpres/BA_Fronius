package scheduled;

import dto.CurrentAcDto;
import fronius.FroniusClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final FroniusClientFactory froniusClientFactory;
    private final boolean currentAc;

    public Scheduler(@Value("${app.run.scheduled.current-ac:false}") boolean currentAc, FroniusClientFactory froniusClientFactory) {
        this.froniusClientFactory = froniusClientFactory;
        this.currentAc = currentAc;
    }

    @Async
    @Scheduled(fixedDelayString = "PT10S", initialDelayString = "PT10S")
    public void startWorkflowForCurrentAC(){
        if (currentAc) {
            CurrentAcDto dto = froniusClientFactory.getFroniusClient().currentAcEndpoint();
            System.out.println(dto);
        }
    }
}
