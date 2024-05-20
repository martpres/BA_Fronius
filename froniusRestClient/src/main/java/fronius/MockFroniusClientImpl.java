package fronius;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import dto.CommonInverterDataDto;
import dto.MeterRealtimeDataDto;
import dto.PowerFlowRealtimeDataDto;
import dto.StorageRealtimeDataDto;
import service.FroniusClient;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

public class MockFroniusClientImpl implements FroniusClient {
    private final String METER_REALTIME_DATA_PATH = "froniusRestClient/src/main/resources/meterRealtimeData.json";
    private final String POWER_FLOW_REALTIME_DATA_PATH = "froniusRestClient/src/main/resources/powerFlowRealtimeData.json";
    private final String COMMON_INVERTER_DATA_PATH = "froniusRestClient/src/main/resources/commonInverterData.json";
    private final String STORAGE_REALTIME_DATA_PATH = "froniusRestClient/src/main/resources/storageRealtimeData.json";
    private final ObjectMapper objectMapper;

    public MockFroniusClientImpl() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override
    public MeterRealtimeDataDto meterRealtimeDataEndpoint() {
        System.out.println("DEBUG: MeterRealtimeData | reading Json file");
        MeterRealtimeDataDto dto1 = readJsonFromFile(METER_REALTIME_DATA_PATH, "$", MeterRealtimeDataDto.class);
        dto1.setTimestamp(ZonedDateTime.now());
        return dto1;
    }

    @Override
    public PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint() {
        System.out.println("DEBUG: PowerFlowRealtimeData | reading Json file");
        PowerFlowRealtimeDataDto dto2 = readJsonFromFile(POWER_FLOW_REALTIME_DATA_PATH, "$", PowerFlowRealtimeDataDto.class);
        dto2.setTimestamp(ZonedDateTime.now());
        return dto2;
    }

    @Override
    public CommonInverterDataDto commonInverterDataEndpoint() {
        System.out.println("DEBUG: CommonInverterData | reading Json file");
        CommonInverterDataDto dto3 = readJsonFromFile(COMMON_INVERTER_DATA_PATH, "$", CommonInverterDataDto.class);
        dto3.setTimestamp(ZonedDateTime.now());
        return dto3;
    }

    @Override
    public StorageRealtimeDataDto storageRealtimeDataEndpoint() {
        System.out.println("DEBUG: StorageRealtimeData | reading Json file");
        StorageRealtimeDataDto dto4 = readJsonFromFile(STORAGE_REALTIME_DATA_PATH, "$", StorageRealtimeDataDto.class);
        dto4.setTimestamp(ZonedDateTime.now());
        return dto4;
    }

    private <T> T readJsonFromFile(final String filePath, final String jsonPath, Class<T> clazz) {
        try {
            return objectMapper.convertValue(JsonPath.parse(new File(filePath)).read(jsonPath), clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not parse json" + e.getMessage());
        }
    }
}
