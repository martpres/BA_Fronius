package fronius;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dto.CurrentAcDto;
import org.springframework.beans.factory.annotation.Autowired;
import service.FroniusClient;

import java.io.File;
import java.io.IOException;

public class MockFroniusClientImpl implements FroniusClient {
    private final String CURRENT_AC_FILE_PATH="src/main/resources/currentAC.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CurrentAcDto currentAcEndpoint() {
        return readJsonFromFile(CURRENT_AC_FILE_PATH, "$.Body.Data", CurrentAcDto.class);
    }

    private <T> T readJsonFromFile(final String filePath, final String jsonPath, Class<T> clazz){
        return JsonPath
                .parse(readFile(filePath))
                .read(jsonPath, clazz);
    }

    private String readFile(final String filePath){
        try {
            return objectMapper.readTree(new File(filePath)).asText();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not red file " + e.getMessage());
        }
    }


}


