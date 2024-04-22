package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponseCurrentAcDto;
import entity.CurrentAC;
import entity.QCurrentAC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.CurrentAcMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class CurrentAcQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QCurrentAC qCurrentAC = QCurrentAC.currentAC;

    private final CurrentAcMapper currentAcMapper;

    public CurrentAcQueryDslRepository(CurrentAcMapper currentAcMapper) {
        this.currentAcMapper = currentAcMapper;
    }

    public QueryDslResponse<ResponseCurrentAcDto> loadCurrentAc(Optional<ZonedDateTime> startDate, Optional<ZonedDateTime> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qCurrentAC.timestamp.after(startDate.get())));
        endDate.ifPresent(value->booleanBuilder.and(qCurrentAC.timestamp.before(endDate.get())));
        JPAQuery<CurrentAC> query = jpaQueryFactory.selectFrom(qCurrentAC).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(currentAcMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qCurrentAC).fetchCount());
    }
}
