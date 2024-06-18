package authservice.auth_service.Service;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Producto;
import authservice.auth_service.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public Compra realizarCompra(String usuarioId, List<Producto> productos) {
        Compra compra = new Compra();
        compra.setUsuarioId(usuarioId);
        compra.setProductos(productos);
        compra.setFecha(LocalDate.now()); 
        compra.setTotal(productos.stream().mapToDouble(p -> p.getPrecio() * p.getCantidad()).sum());
        return compraRepository.save(compra);
    }

    public List<Compra> obtenerComprasPorUsuario(String usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }

    public List<Compra> obtenerTodasLasCompras(){
        return compraRepository.findAll();
    }
}
