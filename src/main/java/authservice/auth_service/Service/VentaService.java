package authservice.auth_service.Service;

import authservice.auth_service.model.EstadoPago;
import authservice.auth_service.model.Producto;
import authservice.auth_service.model.Venta;
import authservice.auth_service.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta realizarVenta(String vendedorId, String clienteId, List<Producto> productos, double montoPagado) {
        double total = calcularTotal(productos);
        EstadoPago estadoPago = montoPagado >= total ? EstadoPago.PAGO_COMPLETO : EstadoPago.PAGO_PARCIAL;
        Venta venta = new Venta(vendedorId, clienteId, productos, total, montoPagado, estadoPago);
        return ventaRepository.save(venta);
    }

    private double calcularTotal(List<Producto> productos) {
        return productos.stream()
                       .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                       .sum();
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
