package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.dto.PedidoDTO;
import com.techlab.ecommerce.entity.EstadoPedido;
import com.techlab.ecommerce.entity.Pedido;
import com.techlab.ecommerce.exception.PedidoNoEncontradoException;
import com.techlab.ecommerce.exception.ProductoNoEncontradoException;
import com.techlab.ecommerce.exception.StockInsuficienteException;
import com.techlab.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody @Valid PedidoDTO dto) {
        try {
            Pedido pedido = pedidoService.crearPedido(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodos();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam EstadoPedido estado) {
        try {
            Pedido pedidoActualizado = pedidoService.cambiarEstado(id, estado);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (PedidoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Pedido>> listarPorEstado(@RequestParam EstadoPedido estado) {
        List<Pedido> pedidos = pedidoService.listarPorEstado(estado);
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidos);
    }
}