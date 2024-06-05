package authservice.auth_service.Service;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Venta;
import authservice.auth_service.repository.CompraRepository;
import authservice.auth_service.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
