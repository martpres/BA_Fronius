package fronius;

import dto.CurrentAcDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import service.FroniusClient;

@Service
public class FroniusClientImpl implements FroniusClient {
    RestClient restClient = RestClient.create();


    @Override
    public CurrentAcDto currentAcEndpoint() {
        return null;
    }
}
