package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.ProductoDTO;
import com.techlab.ecommerce.entity.Producto;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Producto producto = service.buscarPorId(id);
            return ResponseEntity.ok(producto);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody @Valid ProductoDTO dto) {
        Producto nuevo = service.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid ProductoDTO dto) {
        try {
            Producto actualizado = service.actualizar(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria) {

        List<Producto> resultados;

        if (nombre != null) {
            resultados = service.buscarPorNombre(nombre);
        } else if (categoria != null) {
            resultados = service.buscarPorCategoria(categoria);
        } else {
            return ResponseEntity.badRequest().body("Debe especificar un nombre o una categoría.");
        }

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron productos con los criterios de búsqueda.");
        }

        return ResponseEntity.ok(resultados);
    }
}