package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponsePowerFlowRealtimeDataDto;
import entity.PowerFlowRealtimeData;
import entity.QPowerFlowRealtimeData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.PowerFlowRealtimeDataMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class PowerFlowRealtimeDataQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QPowerFlowRealtimeData qPowerFlowRealtimeData = QPowerFlowRealtimeData.powerFlowRealtimeData;

    private final PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper;

    public PowerFlowRealtimeDataQueryDslRepository(PowerFlowRealtimeDataMapper powerFlowRealtimeDataMapper) {
        this.powerFlowRealtimeDataMapper = powerFlowRealtimeDataMapper;
    }

    public QueryDslResponse<ResponsePowerFlowRealtimeDataDto> loadPowerFlowRealtimeData(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qPowerFlowRealtimeData.timestamp.after(startDate.get())));
        endDate.ifPresent(value->booleanBuilder.and(qPowerFlowRealtimeData.timestamp.before(endDate.get())));
        JPAQuery<PowerFlowRealtimeData> query = jpaQueryFactory.selectFrom(qPowerFlowRealtimeData).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qPowerFlowRealtimeData).fetchCount());

    }
}
