package am.wago.mongocollector.repository;

import am.wago.mongocollector.entity.ComponentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentEventRepository extends JpaRepository<ComponentEvent, UUID> {

    Optional<ComponentEvent> findByMongoId(String mongoId);

    List<ComponentEvent> findByCategory(String category);

    List<ComponentEvent> findByManufacturerName(String name);
}
