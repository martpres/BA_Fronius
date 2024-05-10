package repository;

import entity.CurrentAC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface CurrentAcRepository extends CrudRepository<CurrentAC, ZonedDateTime> {
}
