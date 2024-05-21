package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.*;
import entity.MetadataEntity;
import entity.QMetadataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.StorageRealtimeDataMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class MetadataQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QMetadataEntity qMetadataEntity = QMetadataEntity.metadataEntity;

    private final StorageRealtimeDataMapper storageRealtimeDataMapper;

    public MetadataQueryDslRepository(StorageRealtimeDataMapper storageRealtimeDataMapper) {
        this.storageRealtimeDataMapper = storageRealtimeDataMapper;
    }

    public QueryDslResponse<ResponseCapacityStorageDto> loadCapacityStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qMetadataEntity.capacityStorage.isNotNull());
        JPAQuery<MetadataEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(storageRealtimeDataMapper.convertParamsToCapacityStorage(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseManufacturerStorageDto> loadManufacturerStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qMetadataEntity.manufacturerStorage.isNotNull());
        JPAQuery<MetadataEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(storageRealtimeDataMapper.convertParamsToManufacturerStorage(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseModelStorageDto> loadModelStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qMetadataEntity.modelStorage.isNotNull());
        JPAQuery<MetadataEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(storageRealtimeDataMapper.convertParamsToModelStorage(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseSerialStorageDto> loadSerialStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qMetadataEntity.serialStorage.isNotNull());
        JPAQuery<MetadataEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(storageRealtimeDataMapper.convertParamsToSerialStorage(query.fetch()), fetchCount());
    }

    private JPAQuery<MetadataEntity> prepareQuery(BooleanBuilder booleanBuilder, Optional<PageRequest> pageRequest) {
        JPAQuery<MetadataEntity> query = getJpaQueryFactory().selectFrom(qMetadataEntity).where(booleanBuilder);
        pageRequest.ifPresent(value -> query.limit(value.getPageSize()));
        pageRequest.ifPresent(value -> query.offset(value.getOffset()));
        return query;
    }

    private BooleanBuilder prepareBooleanBuilder(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        startDate.ifPresent(value -> booleanBuilder.and(qMetadataEntity.timestamp.after(startDate.get())));
        endDate.ifPresent(value -> booleanBuilder.and(qMetadataEntity.timestamp.before(endDate.get())));
        return booleanBuilder;
    }

    private JPAQueryFactory getJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    public long fetchCount() {
        return getJpaQueryFactory().selectFrom(qMetadataEntity).fetchCount();
    }
}
