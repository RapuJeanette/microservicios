package authservice.auth_service.controller;

import authservice.auth_service.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    @GetMapping("/compras/dia")
    public Map<String, Object> generarReporteComprasDia(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return reporteService.generarReporteComprasDia(fecha);
    }

    @GetMapping("/compras/mes")
    public Map<String, Object> generarReporteComprasMes(@RequestParam("mes") int mes, @RequestParam("anio") int anio) {
        return reporteService.generarReporteComprasMes(mes, anio);
    }

    @GetMapping("/compras/semana")
    public Map<String, Object> generarReporteComprasSemana(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return reporteService.generarReporteComprasSemana(fecha);
    }

    @GetMapping("/compras/anio")
    public Map<String, Object> generarReporteComprasAnio(@RequestParam("anio") int anio) {
        return reporteService.generarReporteComprasAnio(anio);
    }

}
