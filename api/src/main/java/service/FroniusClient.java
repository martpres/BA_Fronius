package service;

import dto.CurrentAcDto;
import dto.PowerDcDto;

public interface FroniusClient {

    CurrentAcDto currentAcEndpoint();
    PowerDcDto powerDcEndpoint();
}
