package authservice.auth_service.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import authservice.auth_service.model.Devolucion;
import authservice.auth_service.model.Producto;
import authservice.auth_service.model.Venta;
import authservice.auth_service.repository.DevolucionRepository;
import authservice.auth_service.repository.ProductoRepository;
import authservice.auth_service.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DevolucionService {
    private static final Logger logger = LoggerFactory.getLogger(DevolucionService.class);

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public List<Devolucion> obtenerTodasDevoluciones() {
        return devolucionRepository.findAll();
    }

    public Devolucion procesarDevolucion(String id) {
        try {
            logger.info("Iniciando proceso de devolución para ventaId: {} ", id);

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID de la venta no puede ser nulo o vacío");
            }

            Optional<Venta> optionalVenta = ventaRepository.findById(id.trim());
            if (!optionalVenta.isPresent()) {
                throw new RuntimeException("Venta no encontrada: " + id);
            }
            Venta venta = optionalVenta.get();

            String productosJson = venta.getProductos();
            if (productosJson == null || productosJson.isEmpty()) {
                throw new RuntimeException("Los productos de la venta no pueden ser nulos o vacíos");
            }

            List<String> productoIds = Arrays.asList(productosJson.split(","));

            // Obtener los objetos completos de producto usando los IDs
            List<Producto> productos = new ArrayList<>();
            for (String productoId : productoIds) {
                Producto producto = productoRepository.findById(productoId.trim())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoId));
                productos.add(producto);
            }
            for (Producto producto : productos) {
                Producto productoEnRepo = productoRepository.findById(producto.getId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + producto.getId()));
                productoEnRepo.setCantidadDisponible(productoEnRepo.getCantidadDisponible() + producto.getCantidad());
                productoRepository.save(productoEnRepo);
            }
            String ventaId = id;
            Devolucion devolucion = new Devolucion(ventaId, "Solicitado", productos);
            return devolucionRepository.save(devolucion);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la devolución: " + e.getMessage(), e);
        }
    }

    ObjectMapper objectMapper = new ObjectMapper();

    private boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
