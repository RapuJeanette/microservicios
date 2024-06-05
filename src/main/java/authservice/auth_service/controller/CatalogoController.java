package authservice.auth_service.controller;

import authservice.auth_service.repository.CatalogoRepository;
import authservice.auth_service.model.Catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogos")
public class CatalogoController {
    @Autowired
    private CatalogoRepository catalogoRepository;

    @GetMapping
    public ResponseEntity<List<Catalogo>> obtenerTodosLosCatalogos() {
        List<Catalogo> catalogos = catalogoRepository.findAll();
        return new ResponseEntity<>(catalogos, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Catalogo> crearCatalogo(@RequestBody Catalogo catalogo) {
        Catalogo nuevoCatalogo = catalogoRepository.save(catalogo);
        return new ResponseEntity<>(nuevoCatalogo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalogo> obtenerCatalogoPorId(@PathVariable("id") String id) {
        Catalogo catalogo = catalogoRepository.findById(id).orElse(null);
        if (catalogo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(catalogo, HttpStatus.OK);
    }
}
