package authservice.auth_service.Service;

import authservice.auth_service.model.Carrito;
import authservice.auth_service.model.ItemCarrito;
import authservice.auth_service.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito obtenerCarritoPorUsuarioId(String usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).orElse(new Carrito(usuarioId, List.of()));
    }

    public Carrito agregarItemAlCarrito(String usuarioId, ItemCarrito item) {
        Carrito carrito = obtenerCarritoPorUsuarioId(usuarioId);
        carrito.getItems().add(item);
        return carritoRepository.save(carrito);
    }

    public Carrito eliminarItemDelCarrito(String usuarioId, String productoId) {
        Carrito carrito = obtenerCarritoPorUsuarioId(usuarioId);
        carrito.getItems().removeIf(item -> item.getProductoId().equals(productoId));
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCantidadItem(String usuarioId, String productoId, int cantidad) {
        Carrito carrito = obtenerCarritoPorUsuarioId(usuarioId);
        for (ItemCarrito item : carrito.getItems()) {
            if (item.getProductoId().equals(productoId)) {
                item.setCantidad(cantidad);
                break;
            }
        }
        return carritoRepository.save(carrito);
    }

    public void vaciarCarrito(String usuarioId) {
        Carrito carrito = obtenerCarritoPorUsuarioId(usuarioId);
        carrito.getItems().clear();
        carritoRepository.save(carrito);
    }
}
