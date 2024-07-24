package mapper;

import dto.ResponsePricesDto;
import entity.PricesEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PricesMapper {

    public abstract ResponsePricesDto entityToDto(PricesEntity entity);

}
