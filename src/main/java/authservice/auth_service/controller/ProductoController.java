package authservice.auth_service.controller;

import authservice.auth_service.model.Producto;
import authservice.auth_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") String id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable("id") String id, @RequestBody Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producto.setId(id); // Asegurarse de que el id del producto a editar sea el mismo que el
                            // proporcionado en la URL
        Producto productoEditado = productoRepository.save(producto);
        return new ResponseEntity<>(productoEditado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") String id, @RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (!productoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        producto.setId(id);
        Producto actualizadoProducto = productoRepository.save(producto);
        return new ResponseEntity<>(actualizadoProducto, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> eliminarProducto(@PathVariable("id") String id) {
        try {
            productoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
