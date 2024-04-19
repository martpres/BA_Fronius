package mapper;

import dto.CurrentAcDto;
import entity.CurrentAC;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class CurrentAcMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract CurrentAC dtoToEntity(CurrentAcDto dto);

    @Mapping(target = "timestamp", source = "createdAt")
    public abstract CurrentAcDto entityToDto(CurrentAC entity);

    public abstract List<CurrentAcDto> entityToDto(List<CurrentAC> entities);
}