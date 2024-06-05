package authservice.auth_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "devoluciones")
public class Devolucion {
    @Id
    private String id;
    private String productoId;
    private String motivo;
    private int cantidad;
    private Date fecha;

    // Constructor, getters y setters

    public Devolucion() {
        this.fecha = new Date();
    }

    public Devolucion(String productoId, String motivo, int cantidad) {
        this.productoId = productoId;
        this.motivo = motivo;
        this.cantidad = cantidad;
        this.fecha = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
