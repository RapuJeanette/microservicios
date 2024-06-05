package authservice.auth_service.controller;

import authservice.auth_service.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/ventas")
    public Map<String, Object> generarReporteVentas() {
        return reporteService.generarReporteVentas();
    }

    @GetMapping("/compras")
    public Map<String, Object> generarReporteCompras() {
        return reporteService.generarReporteCompras();
    }
}
