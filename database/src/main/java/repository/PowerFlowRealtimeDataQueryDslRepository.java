package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponsePowerDcDto;
import dto.ResponsePowerFlowRealtimeDataDto;
import entity.PowerDC;
import entity.QPowerACGrid;
import entity.QPowerDC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.PowerAcGridMapper;
import mapper.PowerDcMapper;
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

    private final QPowerDC qPowerDC = QPowerDC.powerDC;

    private final PowerDcMapper powerDcMapper;

    private final QPowerACGrid qPowerACGrid = QPowerACGrid.powerACGrid;

    private final PowerAcGridMapper powerAcGridMapper;

    public PowerFlowRealtimeDataQueryDslRepository(PowerDcMapper powerDcMapper, PowerAcGridMapper powerAcGridMapper) {
        this.powerDcMapper = powerDcMapper;
        this.powerAcGridMapper = powerAcGridMapper;
    }

    public QueryDslResponse<ResponsePowerFlowRealtimeDataDto> loadPowerDc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qPowerACGrid.timestamp.after(startDate.get())));
        endDate.ifPresent(value->booleanBuilder.and(qPowerACGrid.timestamp.before(endDate.get())));
        JPAQuery<PowerDC> query = jpaQueryFactory.selectFrom(qPowerACGrid).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(powerFlowRealtimeDataMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qPowerFlowRealtimeData).fetchCount());
    }
}
