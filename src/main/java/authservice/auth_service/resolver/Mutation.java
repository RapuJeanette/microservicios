package authservice.auth_service.resolver;

import authservice.auth_service.model.Compra;
import authservice.auth_service.model.Producto;
import authservice.auth_service.Service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    private CompraService compraService;

    public Compra realizarCompra(String usuarioId, List<Producto> productos) {
        return compraService.realizarCompra(usuarioId, productos);
    }
}
