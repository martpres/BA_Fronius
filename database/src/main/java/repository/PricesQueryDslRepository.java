package repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dto.ResponsePricesDto;
import entity.PricesEntity;
import entity.QPricesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mapper.PricesMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Repository
@Transactional(readOnly = true)
public class PricesQueryDslRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final QPricesEntity qPricesEntity = QPricesEntity.pricesEntity;
    private final PricesMapper pricesMapper;

    public PricesQueryDslRepository(PricesMapper pricesMapper) {
        this.pricesMapper = pricesMapper;
    }

    public PricesEntity getLastPriceEntity(){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPricesEntity.endDay.isNull());
        return getJpaQueryFactory().selectFrom(qPricesEntity).where(booleanBuilder).fetchFirst();
    }

    public ResponsePricesDto getLastPrice(){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPricesEntity.endDay.isNull());
        return pricesMapper.entityToDto(getJpaQueryFactory().selectFrom(qPricesEntity).where(booleanBuilder).fetchFirst());
    }

    public ResponsePricesDto getPriceForDate(ZonedDateTime zonedDateTime){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPricesEntity.beginDay.before(zonedDateTime));
        booleanBuilder.and(qPricesEntity.endDay.after(zonedDateTime)).or(qPricesEntity.endDay.isNull());
        return pricesMapper.entityToDto(getJpaQueryFactory().selectFrom(qPricesEntity).where(booleanBuilder).fetchFirst());
    }

    private JPAQueryFactory getJpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
