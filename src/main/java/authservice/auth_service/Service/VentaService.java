package authservice.auth_service.Service;

import authservice.auth_service.model.Producto;
import authservice.auth_service.model.Venta;
import authservice.auth_service.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta realizarVenta(String vendedorId, List<Producto> productos) {
        double total = productos.stream().mapToDouble(p -> p.getPrecio() * p.getCantidad()).sum();
        Venta venta = new Venta(vendedorId, productos, new Date(), total);
        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerVentasPorVendedor(String vendedorId) {
        return ventaRepository.findByVendedorId(vendedorId);
    }

    public Map<String, Object> generarReporteVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        
        double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
        
        Map<String, Long> productosVendidos = ventas.stream()
            .flatMap(venta -> venta.getProductos().stream())
            .collect(Collectors.groupingBy(Producto::getNombre, Collectors.counting()));

        return Map.of(
            "totalVentas", totalVentas,
            "productosVendidos", productosVendidos
        );
    }
}
