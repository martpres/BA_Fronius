package mapper;

import dto.PowerFlowRealtimeDataDto;
import dto.ResponsePowerFlowRealtimeDataDto;
import entity.PowerFlowRealtimeData;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PowerFlowRealtimeDataMapper {

    public abstract PowerFlowRealtimeData dtoToEntity(PowerFlowRealtimeDataDto dto);

    public abstract ResponsePowerFlowRealtimeDataDto entityToDto(PowerFlowRealtimeData entity);

    public abstract List<ResponsePowerFlowRealtimeDataDto> entityToDto(List<PowerFlowRealtimeData> entities);
}
