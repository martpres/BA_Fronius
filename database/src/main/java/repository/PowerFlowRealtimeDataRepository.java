package repository;

import entity.PowerACGrid;
import entity.PowerDC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface PowerFlowRealtimeDataRepository extends CrudRepository<PowerDC, PowerACGrid, ZonedDateTime> {
}
