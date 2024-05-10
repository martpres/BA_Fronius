package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponsePowerDcDto;
import entity.PowerDC;
import entity.QPowerDC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.PowerDcMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class PowerDcQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QPowerDC qPowerDC = QPowerDC.powerDC;

    private final PowerDcMapper powerDcMapper;

    public PowerDcQueryDslRepository(PowerDcMapper powerDcMapper) {
        this.powerDcMapper = powerDcMapper;
    }

    public QueryDslResponse<ResponsePowerDcDto> loadPowerDc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qPowerDC.timestamp.after(startDate.get())));
        endDate.ifPresent(value->booleanBuilder.and(qPowerDC.timestamp.before(endDate.get())));
        JPAQuery<PowerDC> query = jpaQueryFactory.selectFrom(qPowerDC).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(powerDcMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qPowerDC).fetchCount());
    }
}
