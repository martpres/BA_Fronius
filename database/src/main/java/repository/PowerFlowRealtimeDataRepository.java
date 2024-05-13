package repository;

import entity.PowerFlowRealtimeData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface PowerFlowRealtimeDataRepository extends CrudRepository<PowerFlowRealtimeData, ZonedDateTime> {
}
