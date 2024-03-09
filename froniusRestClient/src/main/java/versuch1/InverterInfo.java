package versuch1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InverterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String apiVersion;
    private String baseUrl;
    private String compatibilityRange;







    public String getApiVersion() {
        return apiVersion;
    }
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public String getCompatibilityRange() {
        return compatibilityRange;
    }
    public void setCompatibilityRange(String compatibilityRange) {
        this.compatibilityRange = compatibilityRange;
    }




}
