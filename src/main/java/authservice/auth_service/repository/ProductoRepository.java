package authservice.auth_service.repository;

import authservice.auth_service.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    @Query("{'_id': ?0 }")
    Producto crearProducto(Producto producto);

}
