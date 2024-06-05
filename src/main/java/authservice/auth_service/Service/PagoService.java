package authservice.auth_service.Service;

import authservice.auth_service.model.Pago;
import authservice.auth_service.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public Pago registrarPago(Pago pago) {
        return pagoRepository.save(pago);
    }
}
