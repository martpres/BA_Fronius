package fronius;

import dto.CurrentAcDto;
import dto.PowerFlowRealtimeDataDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import service.FroniusClient;

@Service
public class FroniusClientImpl implements FroniusClient {
    private final RestClient restClient;
    private final String froniusUrl;

    public FroniusClientImpl(@Value("${app.restclient.fronius-url}") String froniusUrl) {
        this.restClient = RestClient.create();
        this.froniusUrl = froniusUrl;
    }

    @Override
    public CurrentAcDto currentAcEndpoint() {
        System.out.println("DEBUG: CurrentAC | sending rest request");
        return restClient.get()
                .uri(froniusUrl + "GetMeterRealtimeData.cgi?Scope=Device&DeviceId=0")
                .retrieve()
                .body(CurrentAcDto.class);
    }

    @Override
    public PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint() {
        System.out.println("DEBUG: PowerFlowRealtimeData | sending rest request");
        return restClient.get()
                .uri(froniusUrl + "GetPowerFlowRealtimeData.fcgi")
                .retrieve()
                .body(PowerFlowRealtimeDataDto.class);
    }






}
