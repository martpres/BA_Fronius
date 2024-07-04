package fronius;

import dto.CommonInverterDataDto;
import dto.MeterRealtimeDataDto;
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
    public MeterRealtimeDataDto meterRealtimeDataEndpoint() {
//        System.out.println("DEBUG: MeterRealtimeData | sending rest request");
        return restClient.get()
                .uri(froniusUrl + "GetMeterRealtimeData.cgi?Scope=Device&DeviceId=0")
                .retrieve()
                .body(MeterRealtimeDataDto.class);
    }

    @Override
    public PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint() {
//        System.out.println("DEBUG: PowerFlowRealtimeData | sending rest request");
        return restClient.get()
                .uri(froniusUrl + "GetPowerFlowRealtimeData.fcgi")
                .retrieve()
                .body(PowerFlowRealtimeDataDto.class);
    }

    @Override
    public CommonInverterDataDto commonInverterDataEndpoint() {
//        System.out.println("DEBUG: CommonInverterData | sending rest request");
        return restClient.get()
                .uri(froniusUrl + "GetInverterRealtimeData.cgi?Scope=Device&DeviceId=1&DataCollection=CommonInverterData")
                .retrieve()
                .body(CommonInverterDataDto.class);
    }

}
