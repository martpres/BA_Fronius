package mapper;

import dto.CurrentAcDto;
import dto.PowerDcDto;
import dto.ResponseCurrentAcDto;
import dto.ResponsePowerDcDto;
import entity.CurrentAC;
import entity.PowerDC;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PowerDcMapper {

    public abstract PowerDC dtoToEntity(PowerDcDto dto);

    public abstract ResponsePowerDcDto entityToDto(PowerDC entity);

    public abstract List<ResponsePowerDcDto> entityToDto(List<PowerDC> entities);
}
