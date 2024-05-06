package repository;

import entity.CurrentAC;
import entity.PowerDC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerDcRepository extends CrudRepository<PowerDC,Integer> {
}
