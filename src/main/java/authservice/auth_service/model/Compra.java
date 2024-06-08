package authservice.auth_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "compras")
public class Compra {
    @Id
    private String id;
    private String usuarioId;
    private List<Producto> productos;
    private String fecha;
    private double total;

    public Compra() {
    }

    public Compra(String usuarioId, List<Producto> productos, String fecha, double total) {
        this.usuarioId = usuarioId;
        this.productos = productos;
        this.fecha = fecha;
        this.total = total;
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
