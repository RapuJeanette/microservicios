package authservice.auth_service.repository;

import authservice.auth_service.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventarioRepository extends MongoRepository<Producto, String> {
    
}
