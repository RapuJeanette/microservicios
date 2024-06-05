package authservice.auth_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carritos")
public class Carrito {
    @Id
    private String id;
    private String usuarioId;
    private List<ItemCarrito> items;

    // Constructor, getters y setters

    public Carrito() {
    }

    public Carrito(String usuarioId, List<ItemCarrito> items) {
        this.usuarioId = usuarioId;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
}
