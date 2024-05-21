package service;

import dto.CommonInverterDataDto;
import dto.MeterRealtimeDataDto;
import dto.PowerFlowRealtimeDataDto;
import dto.StorageRealtimeDataDto;

public interface FroniusClient {

    MeterRealtimeDataDto meterRealtimeDataEndpoint();

    PowerFlowRealtimeDataDto powerFlowRealtimeDataEndpoint();

    CommonInverterDataDto commonInverterDataEndpoint();

    StorageRealtimeDataDto storageRealtimeDataEndpoint();
}
