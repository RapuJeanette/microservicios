package authservice.auth_service.controller;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Producto;
import authservice.auth_service.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping("/crear")
    @ResponseBody
    public Compra realizarCompra(@RequestParam String usuarioId, @RequestBody List<Producto> productos) {
        return compraService.realizarCompra(usuarioId, productos);
    }

    @GetMapping("/{usuarioId}")
    public List<Compra> obtenerComprasPorUsuario(@PathVariable String usuarioId) {
        return compraService.obtenerComprasPorUsuario(usuarioId);
    }

    @GetMapping
    public List<Compra> obtenerTodasLasCompras() {
        return compraService.obtenerTodasLasCompras();
    }
}
