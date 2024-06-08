package authservice.auth_service.resolver;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Producto;
import authservice.auth_service.Service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import authservice.auth_service.controller.ProductoController;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private CompraService compraService;
    private ProductoController productoController;

    public List<Compra> obtenerComprasPorUsuario(String usuarioId) {
        return compraService.obtenerComprasPorUsuario(usuarioId);
    }

    public List<Producto> getAllProducto() {
         ResponseEntity<List<Producto>> response = productoController.obtenerTodosLosProductos();
        return response.getBody();
    }

    public List<Compra> getAllCompras() {
        return compraService.obtenerTodasLasCompras();
    }
}
