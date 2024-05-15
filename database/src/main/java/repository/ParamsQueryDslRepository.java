package repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponseCurrentAcDto;
import dto.ResponsePowerFlowRealtimeDataDto;
import entity.ParamsEntity;
import entity.QParamsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.CurrentAcMapper;
import mapper.PowerFlowRealtimeDataMapper;
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

    private final CurrentAcMapper currentAcMapper;

    private final PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper;

    public ParamsQueryDslRepository(CurrentAcMapper currentAcMapper, PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper) {
        this.currentAcMapper = currentAcMapper;
        this.powerFlowRealtimeDataMapper = powerFlowRealtimeDataMapper;
    }

    public QueryDslResponse<ResponseCurrentAcDto> loadCurrentAc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acPhase1.isNotNull());
        booleanBuilder.and(qParamsEntity.acPhase2.isNotNull());
        booleanBuilder.and(qParamsEntity.acPhase3.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(currentAcMapper.entityToDto(query.fetch()), fetchCount());
    }

    public QueryDslResponse<ResponsePowerFlowRealtimeDataDto> loadPowerFlowRealtimeData(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest) {
        BooleanBuilder booleanBuilder = prepareBooleanBuilder(startDate, endDate);
        booleanBuilder.and(qParamsEntity.acPowerGrid.isNotNull());
        JPAQuery<ParamsEntity> query = prepareQuery(booleanBuilder, pageRequest);
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.entityToDto(query.fetch()), fetchCount());
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
