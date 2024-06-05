package authservice.auth_service.repository;

import authservice.auth_service.model.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends MongoRepository<Pago, String> {
    List<Pago> findByVentaId(String ventaId);
}
