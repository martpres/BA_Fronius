package mapper;

import dto.*;
import entity.ParamsEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PowerFlowRealtimeDataMapper {

    @Mapping(target = "acCurrentGridPhase1", ignore = true)
    @Mapping(target = "acCurrentGridPhase2", ignore = true)
    @Mapping(target = "acCurrentGridPhase3", ignore = true)
    @Mapping(target = "dcVoltagePv", ignore = true)
    @Mapping(target = "acPowerInverter", ignore = true)
    public abstract ParamsEntity dtoToEntity(PowerFlowRealtimeDataDto dto);

    public abstract ResponseDcPowerPvDto convertParamsToDcPowerPv(ParamsEntity entity);
    public abstract List<ResponseDcPowerPvDto> convertParamsToDcPowerPv(List<ParamsEntity> entities);

    public abstract ResponseAcPowerGridDto convertParamsToAcPowerGrid(ParamsEntity entity);
    public abstract List<ResponseAcPowerGridDto> convertParamsToAcPowerGrid(List<ParamsEntity> entities);


    public abstract ResponseDcPowerAkkuDto convertParamsToDcPowerAkku(ParamsEntity entity);
    public abstract List<ResponseDcPowerAkkuDto> convertParamsToDcPowerAkku(List<ParamsEntity> entities);

    public abstract ResponseAcPowerLoadDto convertParamsToAcPowerLoad(ParamsEntity entity);
    public abstract List<ResponseAcPowerLoadDto> convertParamsToAcPowerLoad(List<ParamsEntity> entities);

    public abstract ResponseAutonomyDto convertParamsToAutonomy(ParamsEntity entity);
    public abstract List<ResponseAutonomyDto> convertParamsToAutonomy(List<ParamsEntity> entities);

    public abstract ResponseSelfConsumptionDto convertParamsToSelfConsumption(ParamsEntity entity);
    public abstract List<ResponseSelfConsumptionDto> convertParamsToSelfConsumption(List<ParamsEntity> entities);

    public abstract ResponseStateOfChargeAkkuDto convertParamsToStateOfChargeAkku(ParamsEntity entity);
    public abstract List<ResponseStateOfChargeAkkuDto> convertParamsToStateOfChargeAkku(List<ParamsEntity> entities);



}
