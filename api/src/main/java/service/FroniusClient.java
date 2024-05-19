package service;

import dto.MeterRealtimeDataDto;
import dto.PowerFlowRealtimeDataDto;

public interface FroniusClient {

    MeterRealtimeDataDto meterRealtimeDataEndpoint();

    PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint();
}
