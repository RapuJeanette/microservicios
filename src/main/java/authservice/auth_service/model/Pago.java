package authservice.auth_service.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "pagos")
public class Pago {
    @Id
    private String id;
    private String ventaId;
    private double monto;
    private Date fecha;

    public Pago() {
        this.fecha = new Date();
    }

    public Pago(String ventaId, double monto) {
        this.ventaId = ventaId;
        this.monto = monto;
        this.fecha = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVentaId() {
        return ventaId;
    }

    public void setVentaId(String ventaId) {
        this.ventaId = ventaId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
