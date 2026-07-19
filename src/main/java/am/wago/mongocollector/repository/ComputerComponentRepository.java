package am.wago.mongocollector.repository;

import am.wago.mongocollector.model.ComputerComponent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerComponentRepository extends MongoRepository<ComputerComponent, String> {

    List<ComputerComponent> findByCategory(String category);

    List<ComputerComponent> findByManufacturerName(String name);
}
