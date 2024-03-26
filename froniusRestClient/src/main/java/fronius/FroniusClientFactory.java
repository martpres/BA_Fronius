package fronius;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.FroniusClient;

@Component
public class FroniusClientFactory {

    private final FroniusClient froniusClient;


    public FroniusClientFactory(@Value("${app.mock.restclient:true}") boolean mock, FroniusClient restclient){
        if (mock){
            froniusClient = new MockFroniusClientImpl();
        } else {
            froniusClient = restclient;
        }
    }

    public FroniusClient getFroniusClient() {
        if (froniusClient==null){
            throw new IllegalArgumentException("Fronius Client is Null");
        }
        return froniusClient;
    }
}
