package repository;

import entity.MetadataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface MetadataRepository extends CrudRepository<MetadataEntity, ZonedDateTime> {
}
