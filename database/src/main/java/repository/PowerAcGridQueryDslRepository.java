package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.PowerAcGridDto;
import dto.ResponsePowerAcGridDto;
import dto.ResponsePowerDcDto;
import entity.PowerACGrid;
import entity.QPowerACGrid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.PowerAcGridMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class PowerAcGridQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QPowerACGrid qPowerACGrid = QPowerACGrid.powerACGrid;

    private final PowerAcGridMapper powerAcGridMapper;

    public PowerAcGridQueryDslRepository(PowerAcGridMapper powerAcGridMapper) {
        this.powerAcGridMapper = powerAcGridMapper;
    }

    public QueryDslResponse<ResponsePowerAcGridDto> loadPowerAcGrid(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qPowerACGrid.timestamp.after(startDate.get())));
        endDate.ifPresent(value->booleanBuilder.and(qPowerACGrid.timestamp.before(endDate.get())));
        JPAQuery<PowerACGrid> query = jpaQueryFactory.selectFrom(qPowerACGrid).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(powerAcGridMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qPowerACGrid).fetchCount());
    }
}
