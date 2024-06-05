package authservice.auth_service.repository;

import authservice.auth_service.model.Catalogo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo, String> {
    
}
