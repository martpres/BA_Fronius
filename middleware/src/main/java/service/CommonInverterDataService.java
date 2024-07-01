package service;

import dto.ResponseAcEnergyInverterDayDto;
import dto.ResponseAcPowerInverterDto;
import dto.ResponseDcVoltagePvDto;
import dto.ResponseEnergyDayDto;
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
public class CommonInverterDataService {
    private final ParamsQueryDslRepository paramsQueryDslRepository;
    private final EnergyRepository energyRepository;

    public CommonInverterDataService(ParamsQueryDslRepository paramsQueryDslRepository, EnergyRepository energyRepository) {
        this.paramsQueryDslRepository = paramsQueryDslRepository;
        this.energyRepository = energyRepository;
    }

    public QueryDslResponse<ResponseDcVoltagePvDto> loadDcVoltagePv(Optional<ZonedDateTime> startDate,
                                                                    Optional<ZonedDateTime> endDate,
                                                                    Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadDcVoltagePv(startDate, endDate, pageRequest);
    }

    public QueryDslResponse<ResponseAcPowerInverterDto> loadAcPowerInverter(Optional<ZonedDateTime> startDate,
                                                                            Optional<ZonedDateTime> endDate,
                                                                            Optional<PageRequest> pageRequest) {
        return paramsQueryDslRepository.loadAcPowerInverter(startDate, endDate, pageRequest);
    }

    public ResponseAcEnergyInverterDayDto loadAcEnergyInverterDay(ZonedDateTime startDate,
                                                                  ZonedDateTime endDate) {
        return paramsQueryDslRepository.loadAcEnergyInverterDay(startDate, endDate);
    }

    public ResponseEnergyDayDto loadEnergyDay(ZonedDateTime startDate,
                                              ZonedDateTime endDate) {
        return new ResponseEnergyDayDto(
                energyRepository.calculatePositiveEnergy("ac_power_inverter" ,startDate, endDate)
        );
    }

}
