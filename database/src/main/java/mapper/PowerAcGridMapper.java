package mapper;

import dto.PowerAcGridDto;
import dto.PowerDcDto;
import dto.ResponsePowerAcGridDto;
import dto.ResponsePowerDcDto;
import entity.PowerACGrid;
import entity.PowerDC;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PowerAcGridMapper {

    public abstract PowerACGrid dtoToEntity(PowerAcGridDto dto);

    public abstract ResponsePowerAcGridDto entityToDto(PowerACGrid entity);

    public abstract List<ResponsePowerAcGridDto> entityToDto(List<PowerACGrid> entities);
}
