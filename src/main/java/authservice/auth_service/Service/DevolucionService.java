package authservice.auth_service.Service;

import authservice.auth_service.model.Devolucion;
import authservice.auth_service.model.Producto;
import authservice.auth_service.repository.DevolucionRepository;
import authservice.auth_service.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    public Devolucion procesarDevolucion(String productoId, String motivo, int cantidad) {
        Optional<Producto> optionalProducto = inventarioRepository.findById(productoId);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            if (producto.getCantidadDisponible() >= cantidad) {
                producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
                inventarioRepository.save(producto);
                Devolucion devolucion = new Devolucion(productoId, motivo, cantidad);
                return devolucionRepository.save(devolucion);
            } else {
                throw new RuntimeException("La cantidad solicitada para devolución es mayor que la cantidad disponible.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado en el inventario.");
        }
    }
}
