package authservice.auth_service.controller;

import authservice.auth_service.model.Carrito;
import authservice.auth_service.model.ItemCarrito;
import authservice.auth_service.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @GetMapping("/{usuarioId}")
    public Carrito obtenerCarrito(@PathVariable String usuarioId) {
        return carritoService.obtenerCarritoPorUsuarioId(usuarioId);
    }

    @PostMapping("/{usuarioId}")
    public Carrito agregarItem(@PathVariable String usuarioId, @RequestBody ItemCarrito item) {
        return carritoService.agregarItemAlCarrito(usuarioId, item);
    }

    @PutMapping("/{usuarioId}")
    public Carrito actualizarCantidad(@PathVariable String usuarioId, @RequestParam String productoId, @RequestParam int cantidad) {
        return carritoService.actualizarCantidadItem(usuarioId, productoId, cantidad);
    }

    @DeleteMapping("/{usuarioId}/{productoId}")
    public Carrito eliminarItem(@PathVariable String usuarioId, @PathVariable String productoId) {
        return carritoService.eliminarItemDelCarrito(usuarioId, productoId);
    }

    @DeleteMapping("/{usuarioId}")
    public void vaciarCarrito(@PathVariable String usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
    }
}
