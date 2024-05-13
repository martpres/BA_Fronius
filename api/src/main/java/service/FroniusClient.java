package service;

import dto.CurrentAcDto;
import dto.PowerAcGridDto;
import dto.PowerDcDto;
import dto.PowerFlowRealtimeDataDto;

public interface FroniusClient {

    CurrentAcDto currentAcEndpoint();
    PowerDcDto powerDcEndpoint();
    PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint();
}
