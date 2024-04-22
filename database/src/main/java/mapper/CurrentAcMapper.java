package mapper;

import dto.CurrentAcDto;
import dto.ResponseCurrentAcDto;
import entity.CurrentAC;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CurrentAcMapper {

    public abstract CurrentAC dtoToEntity(CurrentAcDto dto);

    public abstract ResponseCurrentAcDto entityToDto(CurrentAC entity);

    public abstract List<ResponseCurrentAcDto> entityToDto(List<CurrentAC> entities);
}
