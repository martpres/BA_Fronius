package service;

import dto.CommonInverterDataDto;
import dto.MeterRealtimeDataDto;
import dto.PowerFlowRealtimeDataDto;

public interface FroniusClient {

    MeterRealtimeDataDto meterRealtimeDataEndpoint();

    PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint();

    CommonInverterDataDto commonInverterDataEndpoint();
}
