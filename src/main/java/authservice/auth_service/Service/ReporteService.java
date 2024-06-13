package authservice.auth_service.Service;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Venta;
import authservice.auth_service.repository.CompraRepository;
import authservice.auth_service.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CompraRepository compraRepository;

    public Map<String, Object> generarReporteVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        
        double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
        
        Map<String, Long> productosVendidos = ventas.stream()
            .flatMap(venta -> venta.getProductos().stream())
            .collect(Collectors.groupingBy(p -> p.getNombre(), Collectors.counting()));

        return Map.of(
            "totalVentas", totalVentas,
            "productosVendidos", productosVendidos
        );
    }

    public Map<String, Object> generarReporteCompras() {
        List<Compra> compras = compraRepository.findAll();
        
        double totalCompras = compras.stream().mapToDouble(Compra::getTotal).sum();
        
        Map<String, Long> productosComprados = compras.stream()
            .flatMap(compra -> compra.getProductos().stream())
            .collect(Collectors.groupingBy(p -> p.getNombre(), Collectors.counting()));

        return Map.of(
            "totalCompras", totalCompras,
            "productosComprados", productosComprados
        );
    }

    public Map<String, Object> generarReporteComprasDia(LocalDate fecha) {
        LocalDate inicioDia = fecha.atStartOfDay().toLocalDate();
        LocalDate finDia = inicioDia.plusDays(1).atStartOfDay().minusNanos(1).toLocalDate();

        long cantidadCompras = ventaRepository.countByFechaBetween(inicioDia, finDia);
        double totalCompras = sumarTotalComprasPorFecha(inicioDia, finDia);

        return crearReporte("Día", fecha, cantidadCompras, totalCompras);
    }

    public Map<String, Object> generarReporteComprasMes(int mes, int anio) {
        LocalDate inicioMes = LocalDate.of(anio, mes, 1);
        LocalDate finMes = inicioMes.plusMonths(1).minusDays(1);

        long cantidadCompras = ventaRepository.countByFechaBetween(inicioMes, finMes);
        double totalCompras = sumarTotalComprasPorFecha(inicioMes, finMes);

        return crearReporte("Mes", inicioMes, cantidadCompras, totalCompras);
    }

    public Map<String, Object> generarReporteComprasSemana(LocalDate fecha) {
        LocalDate inicioSemana = fecha.with(DayOfWeek.MONDAY); // Obtener el inicio de la semana
        LocalDate finSemana = inicioSemana.plusDays(6); // Obtener el fin de la semana

        long cantidadCompras = ventaRepository.countByFechaBetween(inicioSemana, finSemana);
        double totalCompras = sumarTotalComprasPorFecha(inicioSemana, finSemana);

        return crearReporte("Semana", inicioSemana, cantidadCompras, totalCompras);
    }

    public Map<String, Object> generarReporteComprasAnio(int anio) {
        LocalDate inicioAnio = LocalDate.of(anio, 1, 1);
        LocalDate finAnio = LocalDate.of(anio, 12, 31);

        long cantidadCompras = ventaRepository.countByFechaBetween(inicioAnio, finAnio);
        double totalCompras = sumarTotalComprasPorFecha(inicioAnio, finAnio);

        return crearReporte("Año", inicioAnio, cantidadCompras, totalCompras);
    }

    private double sumarTotalComprasPorFecha(LocalDate inicio, LocalDate fin) {
        List<Venta> compras = ventaRepository.findByFechaBetween(inicio, fin);
        return compras.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    private Map<String, Object> crearReporte(String tipoReporte, LocalDate fecha, long cantidadCompras, double totalCompras) {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("tipoReporte", tipoReporte);
        reporte.put("fecha", fecha);
        reporte.put("cantidadCompras", cantidadCompras);
        reporte.put("totalCompras", totalCompras);
        return reporte;
    }
    
}
