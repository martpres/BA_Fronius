package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.CurrentAcDto;
import entity.CurrentAC;
import entity.QCurrentAC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.CurrentAcMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import response.QueryDslResponse;
import java.time.LocalDate;
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

    public QueryDslResponse<CurrentAcDto> loadCurrentAc(Optional<LocalDate> startDate, Optional<LocalDate> endDate, Optional<PageRequest> pageRequest){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        startDate.ifPresent(value->booleanBuilder.and(qCurrentAC.createdAt.after(startDate.get().atStartOfDay())));
        endDate.ifPresent(value->booleanBuilder.and(qCurrentAC.createdAt.before(endDate.get().atStartOfDay())));
        JPAQuery<CurrentAC> query = jpaQueryFactory.selectFrom(qCurrentAC).where(booleanBuilder);
        pageRequest.ifPresent(value->query.limit(value.getPageSize()));
        pageRequest.ifPresent(value->query.offset(value.getOffset()));
        return new QueryDslResponse<>(currentAcMapper.entityToDto(query.fetch()),jpaQueryFactory.selectFrom(qCurrentAC).fetchCount());
    }
}
