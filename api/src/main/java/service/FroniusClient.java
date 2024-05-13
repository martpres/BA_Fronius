package service;

import dto.CurrentAcDto;
import dto.PowerFlowRealtimeDataDto;

public interface FroniusClient {

    CurrentAcDto currentAcEndpoint();

    PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint();
}
