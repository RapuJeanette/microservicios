package authservice.auth_service.controller;

import authservice.auth_service.model.Pago;
import authservice.auth_service.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
    public Pago registrarPago(@RequestBody Pago pago) {
        return pagoService.registrarPago(pago);
    }
}
