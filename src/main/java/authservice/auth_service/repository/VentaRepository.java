package authservice.auth_service.repository;

import authservice.auth_service.model.Venta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends MongoRepository<Venta, String> {
    List<Venta> findByVendedorId(String vendedorId);
}
