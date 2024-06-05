package authservice.auth_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "ventas")
public class Venta {
    @Id
    private String id;
    private String vendedorId;
    private String clienteId;
    private List<Producto> productos;
    private Date fecha;
    private double total;
    private double montoPagado;
    private EstadoPago estadoPago;

    public Venta() {
        this.fecha = new Date();
    }

    public Venta(String vendedorId, String clienteId, List<Producto> productos, double total, double montoPagado, EstadoPago estadoPago) {
        this.vendedorId = vendedorId;
        this.clienteId = clienteId;
        this.productos = productos;
        this.total = total;
        this.fecha = new Date();
        this.montoPagado = montoPagado;
        this.estadoPago = estadoPago;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(String vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }
}
