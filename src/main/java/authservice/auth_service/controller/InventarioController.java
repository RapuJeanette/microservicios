package authservice.auth_service.controller;

import authservice.auth_service.model.Producto;
import authservice.auth_service.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return inventarioService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable String id) {
        return inventarioService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return inventarioService.agregarProducto(producto);
    }

    @PutMapping
    public Producto actualizarProducto(@RequestBody Producto producto) {
        return inventarioService.actualizarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        inventarioService.eliminarProducto(id);
    }
}
