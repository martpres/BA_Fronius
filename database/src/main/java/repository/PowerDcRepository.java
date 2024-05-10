package repository;

import entity.PowerDC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface PowerDcRepository extends CrudRepository<PowerDC, ZonedDateTime> {
}
