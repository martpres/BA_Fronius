package scheduled;

import dto.CommonInverterDataDto;
import fronius.FroniusClientFactory;
import mapper.CommonInverterDataMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.ParamsRepository;

@Component
public class SchedulerCommonInverterData {

    private final boolean commonInverterData;
    private final ParamsRepository paramsRepository;
    private final CommonInverterDataMapper commonInverterDataMapper;
    private final FroniusClientFactory froniusClientFactory;

    public SchedulerCommonInverterData(@Value("${app.run.scheduled.common-inverter-data:false}") boolean commonInverterData1,
                                       ParamsRepository paramsRepository,
                                       CommonInverterDataMapper commonInverterDataMapper,
                                       FroniusClientFactory froniusClientFactory) {
        this.commonInverterData = commonInverterData1;
        this.paramsRepository = paramsRepository;
        this.commonInverterDataMapper = commonInverterDataMapper;
        this.froniusClientFactory = froniusClientFactory;
    }

    @Async
    @Scheduled(fixedDelayString = "PT15S", initialDelayString = "PT30S")
    public void startWorkflowForCommonInverterData() {
        if (commonInverterData) {
            CommonInverterDataDto dto = froniusClientFactory.getFroniusClient().commonInverterDataEndpoint();
            System.out.println("DEBUG: " + dto.toString());
            paramsRepository.save(commonInverterDataMapper.dtoToEntity(dto));
        }
    }
}
