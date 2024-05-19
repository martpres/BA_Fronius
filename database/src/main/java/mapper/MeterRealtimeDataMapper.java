package mapper;

import dto.MeterRealtimeDataDto;
import dto.ResponseCurrentAcDto;
import entity.ParamsEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class MeterRealtimeDataMapper {

    @Mapping(target = "dcPowerPv", ignore = true)
    @Mapping(target = "acPowerGrid", ignore = true)
    public abstract ParamsEntity dtoToEntity(MeterRealtimeDataDto dto);

    public abstract ResponseCurrentAcDto entityToDto(ParamsEntity entity);

    public abstract List<ResponseCurrentAcDto> entityToDto(List<ParamsEntity> entities);
}
