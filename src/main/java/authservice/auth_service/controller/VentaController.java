package authservice.auth_service.controller;

import authservice.auth_service.model.Producto;
import authservice.auth_service.model.Venta;
import authservice.auth_service.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta realizarVenta(@RequestParam String vendedorId, @RequestBody List<Producto> productos) {
        return ventaService.realizarVenta(vendedorId, productos);
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
