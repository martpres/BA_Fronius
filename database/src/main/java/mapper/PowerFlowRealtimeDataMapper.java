package mapper;

import dto.PowerFlowRealtimeDataDto;
import dto.ResponseAcPowerGridDto;
import dto.ResponseDcPowerPvDto;
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

    @Mapping(target = "acPhase1", ignore = true)
    @Mapping(target = "acPhase2", ignore = true)
    @Mapping(target = "acPhase3", ignore = true)
    public abstract ParamsEntity dtoToEntity(PowerFlowRealtimeDataDto dto);

    public abstract ResponseDcPowerPvDto convertParamsToDcPowerPv(ParamsEntity entity);

    public abstract List<ResponseDcPowerPvDto> convertParamsToDcPowerPv(List<ParamsEntity> entities);

    public abstract ResponseAcPowerGridDto convertParamsToAcPowerGrid(ParamsEntity entity);

    public abstract List<ResponseAcPowerGridDto> convertParamsToAcPowerGrid(List<ParamsEntity> entities);
}
