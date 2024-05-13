package fronius;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import dto.CurrentAcDto;
import dto.PowerFlowRealtimeDataDto;
import service.FroniusClient;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

public class MockFroniusClientImpl implements FroniusClient {
    private final String CURRENT_AC_FILE_PATH="froniusRestClient/src/main/resources/meterRealtimeData.json";
    private final String POWER_FLOW_REALTIME_DATA_PATH="froniusRestClient/src/main/resources/powerFlowRealtimeData..json";
    private final ObjectMapper objectMapper;

    public MockFroniusClientImpl() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override
    public CurrentAcDto currentAcEndpoint() {
        System.out.println("DEBUG: CurrentAC | reading Json file");
        CurrentAcDto dto1 = readJsonFromFile(CURRENT_AC_FILE_PATH, "$", CurrentAcDto.class);
        dto1.setTimestamp(ZonedDateTime.now());
        return dto1;
    }

    @Override
    public PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint() {
        System.out.println("DEBUG: PowerDC | reading Json file");
        System.out.println("DEBUG: PowerACGrid | reading Json file");
        PowerFlowRealtimeDataDto dto2 = readJsonFromFile(POWER_FLOW_REALTIME_DATA_PATH, "$", PowerFlowRealtimeDataDto.class);
        dto2.setTimestamp(ZonedDateTime.now());
        return dto2;
    }

    private <T> T readJsonFromFile(final String filePath, final String jsonPath, Class<T> clazz){
        try {
            return objectMapper.convertValue(JsonPath.parse(new File(filePath)).read(jsonPath), clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not parse json" + e.getMessage());
        }
    }
}
