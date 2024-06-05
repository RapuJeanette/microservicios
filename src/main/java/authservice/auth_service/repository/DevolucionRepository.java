package authservice.auth_service.repository;

import authservice.auth_service.model.Devolucion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DevolucionRepository extends MongoRepository<Devolucion, String> {
    
}
