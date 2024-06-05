package authservice.auth_service.controller;

import authservice.auth_service.model.Producto;
import authservice.auth_service.model.Venta;
import authservice.auth_service.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

     @PostMapping("/realizar")
    public ResponseEntity<Venta> realizarVenta(@RequestParam String vendedorId, @RequestParam String clienteId, @RequestBody List<Producto> productos, @RequestParam double montoPagado) {
        Venta venta = ventaService.realizarVenta(vendedorId, clienteId, productos, montoPagado);
        return new ResponseEntity<>(venta, HttpStatus.CREATED);
    }

    @GetMapping("/{vendedorId}")
    public List<Venta> obtenerVentasPorVendedor(@PathVariable String vendedorId) {
        return ventaService.obtenerVentasPorVendedor(vendedorId);
    }

    @GetMapping("/reporte")
    public Map<String, Object> generarReporteVentas() {
        return ventaService.generarReporteVentas();
    }
}
