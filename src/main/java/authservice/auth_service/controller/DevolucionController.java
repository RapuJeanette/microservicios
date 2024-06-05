package authservice.auth_service.controller;

import authservice.auth_service.model.Devolucion;
import authservice.auth_service.Service.DevolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {
    @Autowired
    private DevolucionService devolucionService;

    @PostMapping
    public Devolucion procesarDevolucion(@RequestBody Devolucion devolucion) {
        return devolucionService.procesarDevolucion(devolucion.getProductoId(), devolucion.getMotivo(), devolucion.getCantidad());
    }
}
