package repository;

import entity.ParamsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface ParamsRepository extends CrudRepository<ParamsEntity, ZonedDateTime> {
}
