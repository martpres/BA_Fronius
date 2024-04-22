package mapper;

import dto.CurrentAcDto;
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

    public abstract CurrentAcDto entityToDto(CurrentAC entity);

    public abstract List<CurrentAcDto> entityToDto(List<CurrentAC> entities);
}
