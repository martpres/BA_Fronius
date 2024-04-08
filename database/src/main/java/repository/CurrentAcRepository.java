package repository;

import entity.CurrentAC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAcRepository extends CrudRepository<CurrentAC,Integer> {
}
