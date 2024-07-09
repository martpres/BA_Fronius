package service;

import dto.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.EnergyRepository;
import repository.ParamsQueryDslRepository;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PowerFlowRealtimeDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;
    private final EnergyRepository energyRepository;

    public PowerFlowRealtimeDataService(ParamsQueryDslRepository paramsQueryDslRepository, EnergyRepository energyRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
        this.energyRepository = energyRepository;
    }

    public QueryDslResponse<ResponseDcPowerPvDto> loadDcPowerPv(Optional<ZonedDateTime> startDate,
                                                                Optional<ZonedDateTime> endDate,
                                                                Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadDcPowerPv(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseAcPowerGridDto> loadAcPowerGrid(Optional<ZonedDateTime> startDate,
                                                                    Optional<ZonedDateTime> endDate,
                                                                    Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadAcPowerGrid(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseDcPowerAkkuDto> loadDcPowerAkku(Optional<ZonedDateTime> startDate,
                                                                    Optional<ZonedDateTime> endDate,
                                                                    Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadDcPowerAkku(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseAcPowerLoadDto> loadAcPowerLoad(Optional<ZonedDateTime> startDate,
                                                                    Optional<ZonedDateTime> endDate,
                                                                    Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadAcPowerLoad(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseAutonomyDto> loadAutonomy(Optional<ZonedDateTime> startDate,
                                                              Optional<ZonedDateTime> endDate,
                                                              Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadAutonomy(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseSelfConsumptionDto> loadSelfConsumption(Optional<ZonedDateTime> startDate,
                                                                            Optional<ZonedDateTime> endDate,
                                                                            Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadSelfConsumption(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseStateOfChargeAkkuDto> loadStateOfChargeAkku(Optional<ZonedDateTime> startDate,
                                                                                Optional<ZonedDateTime> endDate,
                                                                                Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadStateOfChargeAkku(startDate, endDate, pageRequest);
    }

    public ResponseEnergyDayDto loadCalculatedDcEnergyPvDay(ZonedDateTime startDate,
                                                            ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculatePositiveEnergy("dc_power_pv", startDate, endDate)
        );
    }

    public ResponseEnergyDayDto loadCalculatedAcEnergyIntoGridDay(ZonedDateTime startDate,
                                                                  ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculateNegativeEnergy("ac_power_grid", startDate, endDate)
        );
    }

    public ResponseEnergyDayDto loadCalculatedAcEnergyFromGridDay(ZonedDateTime startDate,
                                                                  ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculatePositiveEnergy("ac_power_grid", startDate, endDate)
        );
    }

    public ResponseEnergyDayDto loadCalculatedAcEnergyLoadDay(ZonedDateTime startDate,
                                                              ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculateNegativeEnergy("ac_power_load", startDate, endDate)
        );
    }

    public ResponseEnergyDayDto loadCalculatedDcEnergyIntoAkkuDay(ZonedDateTime startDate,
                                                              ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculateNegativeEnergy("dc_power_akku", startDate, endDate)
        );
    }

    public ResponseEnergyDayDto loadCalculatedDcEnergyFromAkkuDay(ZonedDateTime startDate,
                                                                  ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculatePositiveEnergy("dc_power_akku", startDate, endDate)
        );
    }


}
