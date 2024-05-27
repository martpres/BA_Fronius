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
public abstract class CommonInverterDataMapper {

    @Mapping(target = "acCurrentGridPhase1", ignore = true)
    @Mapping(target = "acCurrentGridPhase2", ignore = true)
    @Mapping(target = "acCurrentGridPhase3", ignore = true)
    @Mapping(target = "dcPowerPv", ignore = true)
    @Mapping(target = "acPowerGrid", ignore = true)
    @Mapping(target = "dcPowerAkku", ignore = true)
    @Mapping(target = "acPowerLoad", ignore = true)
    @Mapping(target = "autonomy", ignore = true)
    @Mapping(target = "selfConsumption", ignore = true)
    @Mapping(target = "stateOfChargeAkku", ignore = true)
    public abstract ParamsEntity dtoToEntity(CommonInverterDataDto dto);

    public abstract ResponseDcVoltagePvDto convertParamsToDcVoltagePv(ParamsEntity entity);

    public abstract List<ResponseDcVoltagePvDto> convertParamsToDcVoltagePv(List<ParamsEntity> entities);

    public abstract ResponseAcPowerInverterDto convertParamsToAcPowerInverterParamsEntity(ParamsEntity entity);

    public abstract List<ResponseAcPowerInverterDto> convertParamsToAcPowerInverter(List<ParamsEntity> entities);
}
