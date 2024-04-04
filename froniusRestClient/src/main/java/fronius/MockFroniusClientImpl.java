package fronius;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import dto.CurrentAcDto;
import org.springframework.beans.factory.annotation.Autowired;
import service.FroniusClient;

import java.io.File;
import java.io.IOException;

public class MockFroniusClientImpl implements FroniusClient {
    private final String CURRENT_AC_FILE_PATH="froniusRestClient/src/main/resources/currentAC.json";

    @Override
    public CurrentAcDto currentAcEndpoint() {
        return readJsonFromFile(CURRENT_AC_FILE_PATH, "$.Body.Data", CurrentAcDto.class);
    }

    private <T> T readJsonFromFile(final String filePath, final String jsonPath, Class<T> clazz){
        try {
            return JsonPath
                    .parse(new File(filePath))
                    .read(jsonPath, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("could not parse json" + e.getMessage());
        }
    }
}
