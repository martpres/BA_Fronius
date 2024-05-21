package mapper;

import dto.*;
import entity.MetadataEntity;
import entity.ParamsEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class StorageRealtimeDataMapper {

    @Mapping(target = "acCurrentGridPhase1", ignore = true)
    @Mapping(target = "acCurrentGridPhase2", ignore = true)
    @Mapping(target = "acCurrentGridPhase3", ignore = true)
    @Mapping(target = "dcVoltagePv", ignore = true)
    @Mapping(target = "acPowerInverter", ignore = true)
    @Mapping(target = "dcPowerPv", ignore = true)
    @Mapping(target = "acPowerGrid", ignore = true)
    @Mapping(target = "dcPowerAkku", ignore = true)
    @Mapping(target = "acPowerLoad", ignore = true)
    @Mapping(target = "autonomy", ignore = true)
    @Mapping(target = "selfConsumption", ignore = true)
    public abstract ParamsEntity dtoToEntityParams(StorageRealtimeDataDto dto);

    public abstract MetadataEntity dtoToEntityMetadata(StorageRealtimeDataDto dto);

    public abstract ResponseStateOfChargeStorageDto convertStateOfChargeStorage(ParamsEntity entity);
    public abstract List<ResponseStateOfChargeStorageDto> convertParamsToStateOfChargeStorage(List<ParamsEntity> entities);

    public abstract ResponseCapacityStorageDto convertCapacityStorage(MetadataEntity entity);
    public abstract List<ResponseCapacityStorageDto> convertParamsToCapacityStorage(List<MetadataEntity> entities);

    public abstract ResponseManufacturerStorageDto convertManufacturerStorage(MetadataEntity entity);
    public abstract List<ResponseManufacturerStorageDto> convertParamsToManufacturerStorage(List<MetadataEntity> entities);

    public abstract ResponseModelStorageDto convertModelStorage(MetadataEntity entity);
    public abstract List<ResponseModelStorageDto> convertParamsToModelStorage(List<MetadataEntity> entities);

    public abstract ResponseSerialStorageDto convertSerialStorage(MetadataEntity entity);
    public abstract List<ResponseSerialStorageDto> convertParamsToSerialStorage(List<MetadataEntity> entities);

}
