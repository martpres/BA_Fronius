package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.*;
import entity.ParamsEntity;
import entity.QParamsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.CommonInverterDataMapper;
import mapper.MeterRealtimeDataMapper;
import mapper.PowerFlowRealtimeDataMapper;
import mapper.StorageRealtimeDataMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ParamsQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QParamsEntity qParamsEntity = QParamsEntity.paramsEntity;


    private final MeterRealtimeDataMapper meterRealtimeDataMapper;

    private final PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper;

    private final CommonInverterDataMapper commonInverterDataMapper;

    private final StorageRealtimeDataMapper storageRealtimeDataMapper;

    public ParamsQueryDslRepository(MeterRealtimeDataMapper meterRealtimeDataMapper, PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper, CommonInverterDataMapper commonInverterDataMapper, StorageRealtimeDataMapper storageRealtimeDataMapper) {
        this.meterRealtimeDataMapper = meterRealtimeDataMapper;
        this.powerFlowRealtimeDataMapper = powerFlowRealtimeDataMapper;
        this.commonInverterDataMapper = commonInverterDataMapper;
        this.storageRealtimeDataMapper = storageRealtimeDataMapper;
    }

    public QueryDslResponse<ResponseAcCurrentGridDto> loadCurrentAc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acCurrentGridPhase1.isNotNull());
        booleanBuilder.and(qParamsEntity.acCurrentGridPhase2.isNotNull());
        booleanBuilder.and(qParamsEntity.acCurrentGridPhase3.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(meterRealtimeDataMapper.entityToDto(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseDcPowerPvDto> loadDcPowerPv(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.dcPowerPv.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToDcPowerPv(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseDcPowerAkkuDto> loadDcPowerAkku(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.dcPowerAkku.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToDcPowerAkku(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseAcPowerLoadDto> loadAcPowerLoad(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acPowerLoad.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToAcPowerLoad(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseAutonomyDto> loadAutonomy(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.autonomy.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToAutonomy(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseSelfConsumptionDto> loadSelfConsumption(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.selfConsumption.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToSelfConsumption(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseAcPowerGridDto> loadAcPowerGrid(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acPowerGrid.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.convertParamsToAcPowerGrid(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseAcPowerInverterDto> loadAcPowerInverter(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acPowerInverter.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(commonInverterDataMapper.convertParamsToAcPowerInverter(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseDcVoltagePvDto> loadDcVoltagePv(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.dcVoltagePv.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(commonInverterDataMapper.convertParamsToDcVoltagePv(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponseStateOfChargeStorageDto> loadStateOfChargeStorage(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.stateOfChargeStorage.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(storageRealtimeDataMapper.convertParamsToStateOfChargeStorage(query.fetch()), fetchCount());
    }

    private JPAQuery<ParamsEntity> prepareQuery(BooleanBuilder booleanBuilder, Optional<PageRequest> pageRequest) {
        JPAQuery<ParamsEntity> query = getJpaQueryFactory().selectFrom(qParamsEntity).where(booleanBuilder);
        pageRequest.ifPresent(value -> query.limit(value.getPageSize()));
        pageRequest.ifPresent(value -> query.offset(value.getOffset()));
        return query;
    }

    private BooleanBuilder prepareBooleanBuilder(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        startDate.ifPresent(value -> booleanBuilder.and(qParamsEntity.timestamp.after(startDate.get())));
        endDate.ifPresent(value -> booleanBuilder.and(qParamsEntity.timestamp.before(endDate.get())));
        return booleanBuilder;
    }

    private JPAQueryFactory getJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    public long fetchCount() {
        return getJpaQueryFactory().selectFrom(qParamsEntity).fetchCount();
    }
}
