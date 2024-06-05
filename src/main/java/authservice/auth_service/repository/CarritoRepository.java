package authservice.auth_service.repository;

import authservice.auth_service.model.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CarritoRepository extends MongoRepository<Carrito, String> {
    Optional<Carrito> findByUsuarioId(String usuarioId);
}
